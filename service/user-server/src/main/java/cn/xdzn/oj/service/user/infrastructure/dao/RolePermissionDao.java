package cn.xdzn.oj.service.user.infrastructure.dao;

import cn.xdzn.oj.service.user.domain.role.model.po.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 角色权限 Dao 层
 *
 * @author HeXin
 * @description 针对表【role_permission】的数据库操作Mapper
 * @createDate 2024-03-08 21:08:14
 * @date 2024/03/08
 */
public interface RolePermissionDao extends BaseMapper<RolePermission> {
    /**
     * 按角色选择权限名称
     *
     * @param roleId 角色 ID
     * @return {@link List}<{@link String}>
     */
    List<String> selectPermissionNameByRole(Long roleId);
}
