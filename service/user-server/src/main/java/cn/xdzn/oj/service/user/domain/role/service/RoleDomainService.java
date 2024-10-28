package cn.xdzn.oj.service.user.domain.role.service;

import cn.xdzn.oj.service.user.domain.role.entity.po.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色服务
 *
 * @author HeXin
 * @description 针对表【role】的数据库操作Service
 * @createDate 2024-03-08 21:08:14
 * @date 2024/03/08
 */
public interface RoleDomainService extends IService<Role> {
    /**
     * 按用户获取角色名称
     *
     * @param id 编号
     * @return {@link List}<{@link String}>
     */
    List<String> getRoleNameByUser(Long id);

    /**
     * 按用户获取角色
     *
     * @param id 编号
     * @return {@link List}<{@link Role}>
     */
    List<Role> getRoleByUser(Long id);

    /**
     * 保存角色
     *
     * @param role 角色
     * @return {@link Boolean}
     */
    Boolean saveRole(Role role);

    /**
     * 删除角色
     *
     * @param roleId 编号
     */
    void removeRole(Long roleId);

    /**
     * 更新角色
     *
     * @param role 角色
     * @return {@link Boolean}
     */
    Boolean updateRole(Role role);

    /**
     * 追加权限
     *
     * @param role 角色
     * @return {@link Role}
     */
    Role appendPermission(Role role);

    /**
     * 绑定权限
     *
     * @param roleId       角色 ID
     * @param permissionId 权限 ID
     * @return {@link Boolean}
     */
    Boolean bindPermission(Long roleId, Long permissionId);

    /**
     * 解绑权限
     *
     * @param roleId       角色 ID
     * @param permissionId 权限 ID
     * @return {@link Boolean}
     */
    Boolean unbindPermission(Long roleId, Long permissionId);
}
