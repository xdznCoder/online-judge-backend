package cn.xdzn.oj.service.user.interfaces.manage;

import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.service.user.application.MessageApplicationService;
import cn.xdzn.oj.service.user.interfaces.dto.MessageUnReadDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息管理
 * @author shelly
 * @date 2024-10-28
 */
@Tag(name = "消息管理")
@RestController
@RequestMapping("/user/message")
@RequiredArgsConstructor
public class MessageManage {
    private final MessageApplicationService messageApplicationService;
    @Operation(summary = "获取未读消息")
    @GetMapping("/unRead")
    public Result<MessageUnReadDTO> unRead() {
        return Result.success(messageApplicationService.getUnReadMessage());
    }
    @Operation(summary = "查看消息列表")
    @GetMapping("/list")
    public Result<?> list(int type) {
        //1为评论，2为回复，3为点赞，4为其他，5为系统
        messageApplicationService.readMessage(type);
        return null;
    }
    @Operation(summary = "删除消息")
    @GetMapping("/delete")
    public Result<Void> delete(@RequestParam @Schema(description = "消息id, 为-1L则删除所有") Long id,
                               @RequestParam(defaultValue = "0", required = false) int type) {
        messageApplicationService.delete(id, type);
        return Result.success();
    }
}
