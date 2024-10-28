package cn.xdzn.oj.service.user.domain.role.service;

import cn.xdzn.oj.service.user.domain.role.entity.po.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色权限服务
 *
 * @author HeXin
 * @description 针对表【role_permission】的数据库操作Service
 * @createDate 2024-03-08 21:08:14
 * @date 2024/03/08
 */
public interface RolePermissionDomainService extends IService<RolePermission> {
    /**
     * 按角色获取权限名称
     *
     * @param roleId 角色 ID
     * @return {@link List}<{@link String}>
     */
    List<String> getPermissionNameByRole(Long roleId);

    /**
     * 按角色获取权限 ID
     *
     * @param roleIds 角色 ID
     * @return {@link List}<{@link Long}>
     */
    List<Long> getPermissionIdsByRole(List<Long> roleIds);

    /**
     * 解除绑定
     *
     * @param roleId       角色 ID
     * @param permissionId 权限 ID
     * @return {@link Boolean}
     */
    Boolean unbind(Long roleId, Long permissionId);
}
