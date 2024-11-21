package cn.xdzn.oj.service.system.interfaces.manage;

import cn.xdzn.oj.common.PageInfo;
import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.common.controller.BaseController;
import cn.xdzn.oj.service.system.domain.notice.entity.po.SystemNotice;
import cn.xdzn.oj.service.system.domain.notice.service.SystemNoticeDomainService;
import cn.xdzn.oj.service.system.interfaces.dto.SystemNoticeDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统通知管理
 * @author shelly
 */
@Tag(name = "系统通知管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/notice")
public class SystemNoticeManage extends BaseController<SystemNoticeDomainService, SystemNotice, SystemNoticeDTO, Long> {


    @Override
    protected Class<SystemNotice> createInstance() {
        return SystemNotice.class;
    }

    @Override
    public Result<Void> save(SystemNoticeDTO instance) {
        return super.save(instance);
    }

    @Override
    public Result<Void> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public Result<SystemNotice> get(Long id) {
        return super.get(id);
    }

    @Override
    public Result<Void> update(SystemNoticeDTO instance) {
        return super.update(instance);
    }
    @Operation(summary = "获取系统通知列表")
    @GetMapping("/list")
    public Result<PageInfo<SystemNotice>> list(
            @RequestParam(required = false, defaultValue = "1") Long page,
            @RequestParam(required = false, defaultValue = "10") Long size
    ) {
        return Result.page(service.lambdaQuery()
                .orderByDesc(SystemNotice::getGmtCreate)
                .eq(SystemNotice::getIsDeleted,0)
                .page(new Page<>(page, size)));
    }

//    @Operation(summary = "系统通知推送指定用户")
//    @PostMapping("/broadcast/{id}")
//    public Result<Void> broadcast(@RequestBody List<Long> ids,@PathVariable Long id){
//        service.broadcastToUsers(ids,id);
//        return Result.success();
//    }
//    @Operation(summary = "系统通知推送所有用户")
//    @PostMapping("/broadcastAll/{uid}")
//    public Result<Void> broadcastAll(@PathVariable Long uid){
//        service.broadcastToAll(uid);
//        return Result.success();
//    }
    @Operation(summary = "一键推送")
    @Async("customTaskExecutor")
    @PostMapping("/broadcast")
    public void broadcast(
            @RequestBody @Nullable List<Long> ids,
            @RequestParam @Schema(description = "1:指定用户通知，2:全体用户通知") int type,
            @RequestParam Long id
     ){
        service.push2Users(ids,type,id);
    }

}
