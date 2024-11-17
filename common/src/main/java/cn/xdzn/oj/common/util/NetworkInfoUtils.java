package cn.xdzn.oj.common.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.system.SystemUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 网络测速工具类
 * @author shelly
 */
@Slf4j
public class NetworkInfoUtils {
    private NetworkInfoUtils(){}

    /**
     * 网速测速时间2s
     */
    private static final int SLEEP_SECONDS = 2;
    private static final String RX_PACKETS = "RX packets";
    private static final String TX_PACKETS = "TX packets";

    /**
     * 获取网络上下行速率，格式{"UP": "123KB/S, "DOWN": "345KB/S"}
     */
    public static Map<String, String> getNetworkUpRate() {
        Map<String, String> result = new HashMap<>();
        Runtime r = Runtime.getRuntime();
        Process pro = null;
        BufferedReader input = null;
        try {
            boolean isWindows = SystemUtil.getOsInfo().isWindows();
            String command = isWindows ? "netstat -e" : "ifconfig";

            // 第一次执行命令并读取结果
            long[] result1;
            try {
                pro = r.exec(command);
                input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
                result1 = readInLine(input, isWindows);
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        log.info(">>> 网络测速失败：", e);
                    }
                }
                if (pro != null) {
                    pro.destroy();
                }
            }
            // 等待一段时间
            Thread.sleep((long) SLEEP_SECONDS * 1000);
            // 第二次执行命令并读取结果
            long[] result2;
            try {
                pro = r.exec(command);
                input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
                result2 = readInLine(input, isWindows);
            } finally {
                try {
                    input.close();
                } catch (IOException e) {
                    log.info(">>> 网络测速异常：", e);
                }
                if (pro != null) {
                    pro.destroy();
                }
            }

            // 计算上行和下行速率
            String upSpeed = FileUtil.readableFileSize(Convert.toLong(
                    NumberUtil.div(NumberUtil.sub(result2[0], result1[0]), SLEEP_SECONDS)));
            String downSpeed = FileUtil.readableFileSize(Convert.toLong(
                    NumberUtil.div(NumberUtil.sub(result2[1], result1[1]), SLEEP_SECONDS)));

            result.put("UP", upSpeed + (upSpeed.endsWith("B") ? "/S" : "B/S"));
            result.put("DOWN", downSpeed + (downSpeed.endsWith("B") ? "/S" : "B/S"));
        } catch (Exception e) {
            log.info(">>> 网络测速失败：", e);
            // 重新中断线程，以便其他代码知道线程被中断
            Thread.currentThread().interrupt();
        }
        return result;
    }


    private static long[] readInLine(BufferedReader input, boolean isWindows) {
        long[] arr = new long[2];
        StringTokenizer tokenStat;
        try {
            if (isWindows) {
                // 获取windows环境下的网口上下行速率
                for(int i = 0; i < 4; i++){
                    input.readLine();
                }
                tokenStat = new StringTokenizer(input.readLine());
                tokenStat.nextToken();
                arr[0] = Long.parseLong(tokenStat.nextToken());
                arr[1] = Long.parseLong(tokenStat.nextToken());
            } else {
                // 获取linux环境下的网口上下行速率
                long rx = 0;
                long tx = 0;
                String line;
                //RX packets:4171603 errors:0 dropped:0 overruns:0 frame:0
                //TX packets:4171603 errors:0 dropped:0 overruns:0 carrier:0
                while ((line = input.readLine()) != null) {
                    if (line.contains(RX_PACKETS)) {
                        rx += Long.parseLong(line.substring(line.indexOf(RX_PACKETS) + 11, line.indexOf(" ",
                                line.indexOf(RX_PACKETS) + 11)));
                    } else if (line.contains(TX_PACKETS)) {
                        tx += Long.parseLong(line.substring(line.indexOf(TX_PACKETS) + 11, line.indexOf(" ",
                                line.indexOf(TX_PACKETS) + 11)));
                    }
                }
                arr[0] = rx;
                arr[1] = tx;
            }
        } catch (Exception e) {
            log.error(">>> 网络测速异常：", e);
        }
        return arr;
    }
}
