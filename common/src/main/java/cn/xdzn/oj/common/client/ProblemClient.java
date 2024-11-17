package cn.xdzn.oj.common.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * 问题客户端
 *
 * @author shelly
 * @date 2024/10/26
 */
@FeignClient("problem-server")
public interface ProblemClient {
    @GetMapping("/problem/monitor")
    Map<String, Long> queryMonitorInfo();
}
