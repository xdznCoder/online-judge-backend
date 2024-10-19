package cn.xdzn.oj.service.user.domain.user.service;

import cn.xdzn.oj.service.user.application.UserRoleService;
import cn.xdzn.oj.service.user.infrastructure.dao.UserRoleDao;
import cn.xdzn.oj.service.user.infrastructure.pojo.UserRole;
import cn.xdzn.oj.service.user.infrastructure.repository.UserRoleManageRepository;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色服务实现类
 *
 * @author HeXin
 * @description 针对表【user_role】的数据库操作Service实现
 * @createDate 2024-03-08 21:08:14
 * @date 2024/03/08
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole>
    implements UserRoleService {

    @Resource
    private UserRoleManageRepository userRoleRepository;

    /**
     * 解除该用户此角色绑定
     *
     * @param userId 用户 ID
     * @param roleId 角色 ID
     * @return {@link Boolean}
     */
    @Override
    public Boolean unbind(Long userId, Long roleId) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,userId).eq(UserRole::getRoleId,roleId);
        return remove(wrapper);
    }

    @Override
    public List<Long> getRoleIdsByUserId(Long uid) {
        return lambdaQuery().eq(UserRole::getUserId, uid).select(UserRole::getRoleId).list()
                .stream().map(obj -> Long.parseLong(obj.toString())).toList();
    }

    @Override
    public List<String> getRoleNamesByUserId(Long id) {
        return userRoleRepository.getRoleNamesByUserId(id);
    }
}




