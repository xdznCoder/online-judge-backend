package cn.xdzn.oj.common.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * 用户客户端
 *
 * @author Onism
 * @date 2024/10/17
 */
@FeignClient("user-server")
public interface UserClient {
    @GetMapping("/training/acNum")
    Map<Integer,Integer> getTrainingAcNum(List<Long> list);
    @GetMapping("/user/ac")
    List<Long> getUserAc();
}
