package cn.xdzn.oj.common.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 问题客户端
 *
 * @author shelly
 * @date 2024/10/26
 */
@FeignClient("problem-server")
public interface ProblemClient {
}
