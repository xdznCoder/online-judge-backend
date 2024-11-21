package cn.xdzn.oj.service.user.interfaces.manage;

import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.service.user.domain.message.entity.po.UserMsg;
import cn.xdzn.oj.service.user.interfaces.dto.MessageUnReadDTO;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "获取未读消息")
    @GetMapping("/unRead")
    public Result<MessageUnReadDTO> unRead() {
        return null;
    }
    @Operation(summary = "查看消息列表")
    @GetMapping("/list")
    public Result<?> list() {
        //标记为已读
        return null;
    }

    @Operation(summary = "查看消息详情")
    @GetMapping("/detail")
    public Result<UserMsg> detail() {
        return null;
    }
    @Operation(summary = "删除消息")
    @GetMapping("/delete")
    public Result<Void> delete(@RequestParam Long id,
                               @RequestParam(defaultValue = "0", required = false) int type) {
        return null;
    }
}
