package cn.xdzn.oj.service.user.domain.user.repository;

import cn.xdzn.oj.service.user.infrastructure.dao.UserRoleDao;
import cn.xdzn.oj.service.user.infrastructure.repository.UserRoleManageRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户角色管理仓库实现类
 *
 * @author Onism
 * @date 2024/10/16
 */
@Component
public class UserRoleManageRepositoryImpl implements UserRoleManageRepository {

    @Resource
    private UserRoleDao userRoleDao;

    @Override
    public List<String> getRoleNamesByUserId(Long uid) {
        return userRoleDao.selectRoleNamesByUserId(uid);
    }
}
