package cn.xdzn.oj.service.user.infrastructure.repository;

import cn.xdzn.oj.service.user.domain.role.repository.RolePermissionRepository;
import cn.xdzn.oj.service.user.infrastructure.dao.RolePermissionDao;
import cn.xdzn.oj.service.user.domain.role.model.po.RolePermission;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色权限管理仓库实现类
 *
 * @author Onism
 * @date 2024/10/16
 */
@Component
public class RolePermissionRepositoryImpl implements RolePermissionRepository {

    @Resource
    private RolePermissionDao rolePermissionDao;

    @Override
    public List<String> getPermissionNameByRole(Long roleId) {
        return rolePermissionDao.selectPermissionNameByRole(roleId);
    }

    @Override
    public List<Long> getPermissionIdsByRole(List<Long> roleIds) {
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(RolePermission::getPermissionId).in(RolePermission::getRoleId,roleIds);
        return rolePermissionDao.selectObjs(wrapper)
                .stream().map(o -> Long.parseLong(o.toString()))
                .toList();
    }
}
