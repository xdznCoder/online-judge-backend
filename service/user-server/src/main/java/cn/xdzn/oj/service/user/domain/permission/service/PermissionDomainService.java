package cn.xdzn.oj.service.user.domain.permission.service;

import cn.xdzn.oj.service.user.domain.permission.model.po.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 权限服务
 *
 * @author HeXin
 * @description 针对表【permission】的数据库操作Service
 * @createDate 2024-03-08 21:08:14
 * @date 2024/03/08
 */
public interface PermissionDomainService extends IService<Permission> {
    /**
     * 获取子权限
     *
     * @param parentId 父 ID
     * @return {@link List}<{@link Permission}>
     */
    List<Permission> getChildren(String parentId);

    /**
     * 按角色获取权限
     *
     * @param roleId 角色 ID
     * @return {@link List}<{@link Long}>
     */
    List<Long> getPermissionByRole(Long roleId);

    /**
     * 按用户获取权限
     *
     * @param id 编号
     * @return {@link List}<{@link String}>
     */
    List<String> getPermissionByUser(Long id);

    /**
     * 删除权限
     *
     * @param id 编号
     * @return {@link Boolean}
     */
    Boolean removePermission(Long id);
}
