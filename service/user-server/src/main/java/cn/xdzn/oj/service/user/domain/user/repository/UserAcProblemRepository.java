package cn.xdzn.oj.service.user.domain.user.repository;

import java.util.List;
import java.util.Map;

/**
 * 用户AC题目仓储接口
 *
 * @author shelly
 * @date 2024/10/26
 */
public interface UserAcProblemRepository {
    Map<Integer,Integer> acNum(List<Long> list);

   List<Long> getUserAc(Long uid);

    Map<Long, Integer> getProblemAcNum(List<Long> ids);
}
