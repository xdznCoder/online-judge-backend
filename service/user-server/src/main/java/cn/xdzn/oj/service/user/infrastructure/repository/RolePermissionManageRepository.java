package cn.xdzn.oj.service.user.infrastructure.repository;

import java.util.List;

/**
 * 角色权限管理仓库
 *
 * @author Onism
 * @date 2024/10/16
 */
public interface RolePermissionManageRepository {
    /**
     * 按角色选择权限名称
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
}
