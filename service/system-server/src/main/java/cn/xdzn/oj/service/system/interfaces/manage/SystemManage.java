package cn.xdzn.oj.service.system.interfaces.manage;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.common.service.FileService;
import cn.xdzn.oj.service.system.domain.system.entity.po.SystemConfig;
import cn.xdzn.oj.service.system.domain.system.service.SystemMonitorDomainService;
import cn.xdzn.oj.service.system.domain.system.service.SystemCarouselDomainService;
import cn.xdzn.oj.service.system.domain.system.service.SystemConfigDomainService;
import cn.xdzn.oj.service.system.interfaces.dto.SystemMonitorServerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import cn.xdzn.oj.service.system.domain.system.entity.po.SystemCarousel;

import java.util.List;

/**
 * 系统管理
 *
 * @author shelly
 * @date 2024/11/16
 */
@Tag(name = "系统管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/system")
public class SystemManage {

    private final SystemCarouselDomainService systemCarouselDomainService;
    private final SystemConfigDomainService systemDomainService;
    private final FileService fileService;
    private final SystemMonitorDomainService monitorServer;

    @GetMapping("/carousel/list")
    @Operation(summary = "轮播图列表")
    public Result<List<String>> list() {
        return Result.success(systemCarouselDomainService.lambdaQuery()
                .select(SystemCarousel::getUrl)
                .eq(SystemCarousel::getIsDeleted,0)
                .list()
                .stream()
                .map(SystemCarousel::getUrl)
                .toList());
    }

    @PostMapping("/carousel")
    @Operation(summary = "添加轮播图")
    public Result<Void> add(@RequestParam("file") MultipartFile file) {
        String url;
        try {
            url = fileService.uploadPicture2Minio(file);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
        systemCarouselDomainService.save(new SystemCarousel()
                .setUrl(url));
        return Result.success();
    }

    @DeleteMapping("/carousel/{id}")
    @Operation(summary = "删除轮播图")
    public Result<Void> delete(@PathVariable Long id) {
        systemCarouselDomainService.removeById(id);
        return Result.success();
    }

    @GetMapping("/config")
    @Operation(summary = "获取系统配置")
    public Result<SystemConfig> get() {
        return Result.success(systemDomainService.getOne(null));
    }

    @PutMapping("/config")
    @Operation(summary = "修改系统配置")
    public Result<Void> updateConfig(@RequestBody SystemConfig systemConfig) {
        systemDomainService.updateById(systemConfig);
        return Result.success();
    }

    @Operation(summary = "获取服务器监控信息")
    @GetMapping("/serverInfo")
    @SaCheckPermission
    public Result<SystemMonitorServerDTO> serverInfo() {
        return Result.success(monitorServer.serverInfo());
    }

}
