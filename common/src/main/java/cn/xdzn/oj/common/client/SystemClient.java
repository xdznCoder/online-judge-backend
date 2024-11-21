package cn.xdzn.oj.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 系统客户端
 *
 * @author shelly
 * @date 2024/10/26
 */
@FeignClient("system-server")
public interface SystemClient {
    @GetMapping("/system/unReadList")
    Integer getNoticeUnRead(Long userId);
    @PostMapping("/system/read")
    void readNotice(Long loginIdAsLong);
    @DeleteMapping("/system/delete")
    void deleteNotice(Long id);
}
