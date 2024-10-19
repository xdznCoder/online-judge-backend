package cn.xdzn.oj.service.user.application;

import cn.xdzn.oj.service.user.infrastructure.pojo.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户角色服务
 *
 * @author HeXin
 * @description 针对表【user_role】的数据库操作Service
 * @createDate 2024-03-08 21:08:14
 * @date 2024/03/08
 */
public interface UserRoleService extends IService<UserRole> {
    /**
     * 解除角色绑定
     *
     * @param userId 用户 ID
     * @param roleId 角色 ID
     * @return {@link Boolean}
     */
    Boolean unbind(Long userId, Long roleId);

    /**
     * 按用户 ID 获取角色 ID
     *
     * @param uid 编号
     * @return {@link List}<{@link Long}>
     */
    List<Long> getRoleIdsByUserId(Long uid);

    /**
     * 按用户 ID 获取角色名称
     *
     * @param id 编号
     * @return {@link List}<{@link String}>
     */
    List<String> getRoleNamesByUserId(Long id);
}

