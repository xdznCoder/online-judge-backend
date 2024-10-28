package cn.xdzn.oj.service.user.domain.permission.repository;

import cn.xdzn.oj.service.user.domain.permission.entity.po.Permission;

import java.util.List;

/**
 * 权限管理存储库
 *
 * @author Onism
 * @date 2024/10/16
 */
public interface PermissionRepository {

    /**
     * 获取子节点
     *
     * @param parentId 父 ID
     * @return {@link List}<{@link Permission}>
     */
    List<Permission> getChildren(String parentId);

    /**
     * 按用户获取权限
     *
     * @param uid 用户 ID
     * @return {@link List}<{@link Permission}>
     */
    List<Permission> getPermissionByUser(Long uid);
}
