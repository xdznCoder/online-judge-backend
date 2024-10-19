package cn.xdzn.oj.service.user.infrastructure.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户客户端
 *
 * @author Onism
 * @date 2024/10/17
 */
@FeignClient("user-server")
public interface UserClient {
}
