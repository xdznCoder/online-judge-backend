package cn.xdzn.oj.service.user.infrastructure.dao;

import cn.xdzn.oj.service.user.domain.permission.entity.po.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 权限 Dao 层
 *
 * @author HeXin
 * @description 针对表【permission】的数据库操作Mapper
 * @createDate 2024-03-08 21:08:14
 * @date 2024/03/08
 */
public interface PermissionDao extends BaseMapper<Permission> {
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
