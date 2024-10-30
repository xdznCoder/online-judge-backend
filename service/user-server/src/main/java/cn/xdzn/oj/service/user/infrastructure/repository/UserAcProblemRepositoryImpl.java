package cn.xdzn.oj.service.user.infrastructure.repository;

import cn.dev33.satoken.stp.StpUtil;
import cn.xdzn.oj.service.user.domain.user.entity.po.UserAcproblem;
import cn.xdzn.oj.service.user.domain.user.repository.UserAcProblemRepository;
import cn.xdzn.oj.service.user.infrastructure.dao.UserAcproblemDao;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 用户ac管理仓库实现类
 *
 * @author shelly
 * @date 2024/10/26
 */
@Component
@RequiredArgsConstructor
public class UserAcProblemRepositoryImpl implements UserAcProblemRepository {

    private final UserAcproblemDao userAcProblemDao;

    @Override
    public Map<Integer,Integer> acNum(List<Long> list) {
        Long userId = StpUtil.getLoginIdAsLong();
        return userAcProblemDao.getAcNum(userId,list);
    }

    @Override
    public List<Long> getUserAc(Long uid) {
        return userAcProblemDao.getUserAc(uid);
    }

    @Override
    public Map<Long, Integer> getProblemAcNum(List<Long> ids) {
        return userAcProblemDao.getProblemAcNum(ids);
    }
}
