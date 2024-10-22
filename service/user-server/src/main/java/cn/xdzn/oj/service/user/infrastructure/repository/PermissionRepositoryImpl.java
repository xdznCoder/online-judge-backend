package cn.xdzn.oj.service.user.infrastructure.repository;

import cn.xdzn.oj.service.user.domain.permission.repository.PermissionRepository;
import cn.xdzn.oj.service.user.infrastructure.dao.PermissionDao;
import cn.xdzn.oj.service.user.domain.permission.model.po.Permission;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 权限管理仓库实现类
 *
 * @author Onism
 * @date 2024/10/16
 */
@Component
public class PermissionRepositoryImpl implements PermissionRepository {

    @Resource
    private PermissionDao permissionDao;

    @Override
    public List<Permission> getChildren(String parentId) {
        return permissionDao.getChildren(parentId);
    }

    @Override
    public List<Permission> getPermissionByUser(Long uid) {
        return permissionDao.getPermissionByUser(uid);
    }
}
