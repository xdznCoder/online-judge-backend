package cn.xdzn.oj.service.system.domain.config.service.impl;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.SystemPropsUtil;
import cn.hutool.system.JvmInfo;
import cn.hutool.system.OsInfo;
import cn.hutool.system.RuntimeInfo;
import cn.hutool.system.SystemUtil;
import cn.xdzn.oj.common.client.ProblemClient;
import cn.xdzn.oj.common.client.UserClient;
import cn.xdzn.oj.common.util.NetworkInfoUtils;
import cn.xdzn.oj.service.system.domain.config.service.SystemMonitorDomainService;
import cn.xdzn.oj.service.system.interfaces.dto.SystemMonitorServerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;
import oshi.SystemInfo;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 监控相关接口实现类
 * @author shelly
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SystemMonitorDomainServiceImpl implements SystemMonitorDomainService {
    private final UserClient userClient;
    private final ProblemClient problemClient;
    @Override
    public SystemMonitorServerDTO  serverInfo() {
        SystemMonitorServerDTO devMonitorServerResult = new SystemMonitorServerDTO();
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor cpu = hal.getProcessor();
        // oj数据封
        Map<String, Long> map = userClient.queryMonitorInfo();
        Map<String, Long> problemMap = problemClient.queryMonitorInfo();
        devMonitorServerResult.setPassCount(map.getOrDefault("passCount",0L));
        devMonitorServerResult.setUserCount(map.getOrDefault("userCount",0L));
        devMonitorServerResult.setProblemCount(problemMap.getOrDefault("problemCount",0L));
        devMonitorServerResult.setSubmitCount(problemMap.getOrDefault("submitCount",0L));

        // CPU信息
        SystemMonitorServerDTO.MonitorCpuInfo devMonitorCpuInfo = new SystemMonitorServerDTO.MonitorCpuInfo();
        devMonitorCpuInfo.setCpuName(CharSequenceUtil.trim(cpu.getProcessorIdentifier().getName()));
        devMonitorCpuInfo.setCpuNum(cpu.getPhysicalPackageCount() + "颗物理CPU");
        devMonitorCpuInfo.setCpuPhysicalCoreNum(cpu.getPhysicalProcessorCount() + "个物理核心");
        devMonitorCpuInfo.setCpuLogicalCoreNum(cpu.getLogicalProcessorCount() + "个逻辑核心");
        long[] prevTicks = cpu.getSystemCpuLoadTicks();
        Util.sleep(1000);
        long[] ticks = cpu.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()]
                - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softIrq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()]
                - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long sys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()]
                - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()]
                - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long ioWait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()]
                - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()]
                - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + sys + idle + ioWait + irq + softIrq + steal;
        devMonitorCpuInfo.setCpuSysUseRate(NumberUtil.div(NumberUtil.mul(sys, 100), totalCpu, 2) + "%");
        devMonitorCpuInfo.setCpuUserUseRate(NumberUtil.div(NumberUtil.mul(user, 100), totalCpu, 2) + "%");
        devMonitorCpuInfo.setCpuTotalUseRate(NumberUtil.div(NumberUtil.mul(NumberUtil.add(sys, user), 100), totalCpu, 2));
        devMonitorCpuInfo.setCpuWaitRate(NumberUtil.div(NumberUtil.mul(ioWait, 100), totalCpu, 2) + "%");
        devMonitorCpuInfo.setCpuFreeRate(NumberUtil.div(NumberUtil.mul(idle, 100), totalCpu, 2) + "%");
        devMonitorServerResult.setDevMonitorCpuInfo(devMonitorCpuInfo);

        // 内存信息
        GlobalMemory memory = hal.getMemory();
        SystemMonitorServerDTO.MonitorMemoryInfo devMonitorMemoryInfo = new SystemMonitorServerDTO.MonitorMemoryInfo();
        long used = memory.getTotal() - memory.getAvailable();
        devMonitorMemoryInfo.setMemoryTotal(FileUtil.readableFileSize(memory.getTotal()));
        devMonitorMemoryInfo.setMemoryUsed(FileUtil.readableFileSize(used));
        devMonitorMemoryInfo.setMemoryFree(FileUtil.readableFileSize(memory.getAvailable()));
        devMonitorMemoryInfo.setMemoryUseRate(NumberUtil.mul(NumberUtil.div(used, memory.getTotal(), 4), 100));
        devMonitorServerResult.setDevMonitorMemoryInfo(devMonitorMemoryInfo);

        // 存储信息
        SystemMonitorServerDTO.MonitorStorageInfo devMonitorStorageInfo = new SystemMonitorServerDTO.MonitorStorageInfo();
        OperatingSystem operatingSystem =  si.getOperatingSystem();
        FileSystem fileSystem = operatingSystem.getFileSystem();
        AtomicLong storageTotal = new AtomicLong();
        AtomicLong storageUsed = new AtomicLong();
        AtomicLong storageFree = new AtomicLong();
        fileSystem.getFileStores().forEach(osFileStore -> {
            long totalSpace = osFileStore.getTotalSpace();
            long usableSpace = osFileStore.getUsableSpace();
            long freeSpace = osFileStore.getFreeSpace();
            long usedSpace = totalSpace - usableSpace;
            storageTotal.addAndGet(totalSpace);
            storageUsed.addAndGet(usedSpace);
            storageFree.addAndGet(freeSpace);
        });
        devMonitorStorageInfo.setStorageTotal(FileUtil.readableFileSize(storageTotal.get()));
        devMonitorStorageInfo.setStorageUsed(FileUtil.readableFileSize(storageUsed.get()));
        devMonitorStorageInfo.setStorageFree(FileUtil.readableFileSize(storageFree.get()));
        devMonitorStorageInfo.setStorageUseRate(NumberUtil.mul(NumberUtil.div(storageUsed.doubleValue(), storageTotal.doubleValue(), 4), 100));
        devMonitorServerResult.setDevMonitorStorageInfo(devMonitorStorageInfo);

        // 服务器信息
        OsInfo osInfo = SystemUtil.getOsInfo();
        SystemMonitorServerDTO.MonitorServerInfo devMonitorServerInfo = new SystemMonitorServerDTO.MonitorServerInfo();
        devMonitorServerInfo.setServerName(NetUtil.getLocalHostName());
        devMonitorServerInfo.setServerOs(osInfo.getName());
        devMonitorServerInfo.setServerIp(NetUtil.getLocalhostStr());
        devMonitorServerInfo.setServerArchitecture(osInfo.getArch());
        devMonitorServerResult.setDevMonitorServerInfo(devMonitorServerInfo);

        // JVM信息
        SystemMonitorServerDTO.MonitorJvmInfo devMonitorJvmInfo = new SystemMonitorServerDTO.MonitorJvmInfo();
        RuntimeInfo runtimeInfo = SystemUtil.getRuntimeInfo();
        JvmInfo jvmInfo = SystemUtil.getJvmInfo();
        devMonitorJvmInfo.setJvmName(jvmInfo.getName());
        devMonitorJvmInfo.setJvmVersion(jvmInfo.getVersion());
        long totalMemory = runtimeInfo.getTotalMemory();
        devMonitorJvmInfo.setJvmMemoryTotal(FileUtil.readableFileSize(totalMemory));
        devMonitorJvmInfo.setJvmMemoryFree(FileUtil.readableFileSize(runtimeInfo.getFreeMemory()));
        long jvmMemoryUsed = NumberUtil.sub(new BigDecimal(runtimeInfo
                .getTotalMemory()), new BigDecimal(runtimeInfo.getFreeMemory())).longValue();
        devMonitorJvmInfo.setJvmMemoryUsed(FileUtil.readableFileSize(jvmMemoryUsed));
        double jvmUseRate = NumberUtil.mul(NumberUtil.div(jvmMemoryUsed, totalMemory, 4), 100);
        devMonitorJvmInfo.setJvmUseRate(jvmUseRate);
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        DateTime startTime = DateUtil.date(runtimeMXBean.getStartTime());
        devMonitorJvmInfo.setJvmStartTime(DateUtil.formatDateTime(startTime));
        devMonitorJvmInfo.setJvmRunTime(DateUtil.formatBetween(startTime, DateTime.now()));
        devMonitorJvmInfo.setJavaVersion(SystemPropsUtil.get("java.version", false));
        devMonitorJvmInfo.setJavaPath(SystemPropsUtil.get("java.home", false));
        devMonitorServerResult.setDevMonitorJvmInfo(devMonitorJvmInfo);

        // 网络信息
        Map<String, String> networkUpRate = NetworkInfoUtils.getNetworkUpRate();
        SystemMonitorServerDTO.MonitorNetworkInfo monitorNetworkInfoDTO = new SystemMonitorServerDTO.MonitorNetworkInfo();
        monitorNetworkInfoDTO.setUpLinkRate(networkUpRate.get("UP"));
        monitorNetworkInfoDTO.setDownLinkRate(networkUpRate.get("DOWN"));
        devMonitorServerResult.setDevMonitorNetworkInfo(monitorNetworkInfoDTO);

        return devMonitorServerResult;
    }
}
