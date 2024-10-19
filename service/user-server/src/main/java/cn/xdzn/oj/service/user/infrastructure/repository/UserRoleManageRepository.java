package cn.xdzn.oj.service.user.infrastructure.repository;

import java.util.List;

/**
 * 用户角色 管理仓库
 *
 * @author Onism
 * @date 2024/10/16
 */
public interface UserRoleManageRepository {
    /**
     * 按用户 ID 选择角色名称
     * @param uid 用户 ID
     * @return {@link List}<{@link String}>
     */
    List<String> getRoleNamesByUserId(Long uid);
}
