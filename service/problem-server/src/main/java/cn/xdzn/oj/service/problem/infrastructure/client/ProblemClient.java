package cn.xdzn.oj.service.problem.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 题目客户端
 *
 * @author shelly
 * @date 2024/10/20
 */
@FeignClient("problem-server")
public interface ProblemClient {
}
