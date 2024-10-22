package cn.xdzn.oj.service.user.domain.role.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.xdzn.oj.common.cache.Cache;
import cn.xdzn.oj.common.cache.CacheParam;
import cn.xdzn.oj.common.constants.RedisConstants;
import cn.xdzn.oj.service.user.domain.role.service.RolePermissionDomainService;
import cn.xdzn.oj.service.user.domain.role.service.RoleDomainService;
import cn.xdzn.oj.service.user.domain.user.service.UserRoleDomainService;
import cn.xdzn.oj.service.user.infrastructure.dao.RoleDao;
import cn.xdzn.oj.service.user.domain.role.model.po.Role;
import cn.xdzn.oj.service.user.domain.role.model.po.RolePermission;
import cn.xdzn.oj.service.user.domain.user.model.po.UserRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 角色服务实现类
 *
 * @author HeXin
 * @description 针对表【role】的数据库操作Service实现
 * @createDate 2024-03-08 21:08:14
 * @date 2024/03/08
 */
@Service
public class RoleDomainServiceImpl extends ServiceImpl<RoleDao, Role>
    implements RoleDomainService {

    @Resource
    private UserRoleDomainService userRoleService;

    @Resource
    private RolePermissionDomainService rolePermissionService;

    @Override
    @Cache(constants = RedisConstants.USER_ROLE)
    public List<String> getRoleNameByUser(@CacheParam Long id) {
        return getRoleByUser(id).stream().map(Role::getRoleName).toList();
    }

    @Override
    public List<Role> getRoleByUser(Long id) {
        List<Long> roleIdList = userRoleService.lambdaQuery().select(UserRole::getRoleId)
                .eq(UserRole::getUserId, id).list()
                .stream().map(UserRole::getRoleId).toList();
        if(CollUtil.isEmpty(roleIdList)){
            return Collections.emptyList();
        }
        return lambdaQuery().in(Role::getId,roleIdList).list();
    }

    @Override
    public Boolean saveRole(Role role) {
        boolean saved = save(role);
        if(saved && !role.getPermissions().isEmpty()){
            return bindPermission(role.getId(),role.getPermissions());
        }
        return saved;
    }

    private boolean  bindPermission(Long roleId,List<Long> permissionIdList){
        unbindPermission(roleId);
        List<RolePermission> rolePermissionList = permissionIdList.stream()
                .map(permission -> new RolePermission().setRoleId(roleId).setPermissionId(permission)).toList();
        return rolePermissionService.saveBatch(rolePermissionList);
    }

    private void unbindPermission(Long roleId){
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId,roleId);
        rolePermissionService.remove(wrapper);
    }

    @Override
    public Role appendPermission(Role role) {
        List<String> permissionNameByRole = rolePermissionService.getPermissionNameByRole(role.getId());
        List<Long> permissionIds = rolePermissionService.getPermissionIdsByRole(ListUtil.of(role.getId()));
        return role.setPermissionNames(permissionNameByRole).setPermissions(permissionIds);
    }

    @Override
    public Boolean bindPermission(Long roleId, Long permissionId) {
        RolePermission rolePermission = new RolePermission().setPermissionId(permissionId).setRoleId(roleId);
        return rolePermissionService.save(rolePermission);
    }

    @Override
    public Boolean unbindPermission(Long roleId, Long permissionId) {
        return rolePermissionService.unbind(roleId,permissionId);
    }

    @Override
    public void removeRole(Long roleId) {
        unbindPermission(roleId);
        removeById(roleId);
    }

    @Override
    public Boolean updateRole(Role role) {
        if(!role.getPermissions().isEmpty()){
            return bindPermission(role.getId(),role.getPermissions());
        }
        return updateById(role);
    }
}




