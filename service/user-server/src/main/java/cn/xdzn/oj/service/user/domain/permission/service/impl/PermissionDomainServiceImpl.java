package cn.xdzn.oj.service.user.domain.permission.service.impl;


import cn.xdzn.oj.common.cache.Cache;
import cn.xdzn.oj.common.cache.CacheParam;
import cn.xdzn.oj.common.constants.RedisConstants;
import cn.xdzn.oj.common.exception.CustomException;
import cn.xdzn.oj.service.user.domain.permission.service.PermissionDomainService;
import cn.xdzn.oj.service.user.domain.role.service.RolePermissionDomainService;
import cn.xdzn.oj.service.user.domain.permission.repository.PermissionRepository;
import cn.xdzn.oj.service.user.infrastructure.dao.PermissionDao;
import cn.xdzn.oj.service.user.domain.permission.entity.po.Permission;
import cn.xdzn.oj.service.user.domain.role.entity.po.RolePermission;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限服务实现类
 *
 * @author HeXin
 * @description 针对表【permission】的数据库操作Service实现
 * @createDate 2024-03-08 21:08:14
 * @date 2024/03/08
 */
@Service
public class PermissionDomainServiceImpl extends ServiceImpl<PermissionDao, Permission>
    implements PermissionDomainService {

    @Resource
    private RolePermissionDomainService rolePermissionService;

    @Resource
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> getChildren(String parentId) {
        return permissionRepository.getChildren(parentId);
    }

    @Override
    public List<Long> getPermissionByRole(Long roleId) {
        List<Long> permissionIdList = rolePermissionService.lambdaQuery()
                .select(RolePermission::getPermissionId)
                // 此时得到的是权限对象的集合
                .eq(RolePermission::getRoleId, roleId).list()
                .stream().map(RolePermission::getPermissionId).toList();
        if(permissionIdList.isEmpty()){
            return Collections.emptyList();
        }
        return lambdaQuery().in(Permission::getId,permissionIdList)
                .list().stream()
                .map(Permission::getId).toList();
    }

    @Override
    @Cache(constants = RedisConstants.USER_PERMISSION)
    public List<String> getPermissionByUser(@CacheParam Long id) {
        List<Permission> allPermissions = list();
        List<Permission> permissions = permissionRepository.getPermissionByUser(id);
        return getSubPermissions(allPermissions,permissions).stream().map(Permission::getKeyName).toList();
    }

    /**
     * 获取子权限
     *
     * @param allPermissions    所有权限
     * @param parentPermissions 父权限
     * @return {@link Set}<{@link Permission}>
     */
    public static Set<Permission> getSubPermissions(List<Permission> allPermissions, List<Permission> parentPermissions){
        Set<Permission> subPermissions = new HashSet<>();
        for (Permission parentPermission : parentPermissions) {
            subPermissions.add(parentPermission);
            getSubPermissionRecursively(allPermissions, parentPermission.getId(), subPermissions);
        }
        return subPermissions;
    }

    /**
     * 递归获取子权限
     *
     * @param allPermissions 所有权限
     * @param parentId       父 ID
     * @param subPermissions 子权限
     */
    public static void getSubPermissionRecursively(List<Permission> allPermissions, Long parentId, Set<Permission> subPermissions){
        for (Permission permission : allPermissions) {
            if(permission.getParentId().equals(parentId)){
                subPermissions.add(permission);
                getSubPermissionRecursively(allPermissions, permission.getId(), subPermissions);
            }
        }
    }

    @Override
    public Boolean removePermission(Long id) {
        Long count = lambdaQuery().eq(Permission::getParentId, id).count();
        if(count > 0){
            throw new CustomException("存在子权限，无法删除");
        }
        return removeById(id);
    }
}




