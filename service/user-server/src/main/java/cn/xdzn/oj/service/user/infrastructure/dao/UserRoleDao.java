package cn.xdzn.oj.service.user.infrastructure.dao;

import cn.xdzn.oj.service.user.infrastructure.pojo.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 用户角色映射器
 *
 * @author HeXin
 * @description 针对表【user_role】的数据库操作Mapper
 * @createDate 2024-03-08 21:08:14
 * @date 2024/03/08
 */
public interface UserRoleDao extends BaseMapper<UserRole> {
    /**
     * 按用户 ID 选择角色名称
     * @param uid 用户 ID
     * @return {@link List}<{@link String}>
     */
    List<String> selectRoleNamesByUserId(Long uid);
}
