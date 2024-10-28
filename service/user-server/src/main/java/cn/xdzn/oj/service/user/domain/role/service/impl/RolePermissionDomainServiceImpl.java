package cn.xdzn.oj.service.user.domain.role.service.impl;

import cn.xdzn.oj.service.user.domain.role.service.RolePermissionDomainService;
import cn.xdzn.oj.service.user.domain.role.repository.RolePermissionRepository;
import cn.xdzn.oj.service.user.infrastructure.dao.RolePermissionDao;
import cn.xdzn.oj.service.user.domain.role.entity.po.RolePermission;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色权限服务实现类
 *
 * @author HeXin
 * @description 针对表【role_permission】的数据库操作Service实现
 * @createDate 2024-03-08 21:08:14
 * @date 2024/03/08
 */
@Service
public class RolePermissionDomainServiceImpl extends ServiceImpl<RolePermissionDao, RolePermission>
    implements RolePermissionDomainService {

    @Resource
    private RolePermissionRepository rolePermissionRepository;

    @Override
    public List<String> getPermissionNameByRole(Long roleId) {
        return rolePermissionRepository.getPermissionNameByRole(roleId);
    }

    @Override
    public List<Long> getPermissionIdsByRole(List<Long> roleIds) {
        return rolePermissionRepository.getPermissionIdsByRole(roleIds);
    }

    @Override
    public Boolean unbind(Long roleId, Long permissionId) {
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId,roleId).eq(RolePermission::getPermissionId,permissionId);
        return remove(wrapper);
    }
}




