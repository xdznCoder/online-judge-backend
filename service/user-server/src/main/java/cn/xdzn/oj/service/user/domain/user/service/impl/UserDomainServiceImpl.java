package cn.xdzn.oj.service.user.domain.user.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.xdzn.oj.common.constants.CodeEnum;
import cn.xdzn.oj.common.constants.RedisConstants;
import cn.xdzn.oj.common.exception.CustomException;
import cn.xdzn.oj.common.util.EmailUtils;
import cn.xdzn.oj.common.util.PasswordUtils;
import cn.xdzn.oj.common.util.RedisUtil;
import cn.xdzn.oj.common.util.ValidateCodeUtils;
import cn.xdzn.oj.service.user.domain.role.service.RoleDomainService;
import cn.xdzn.oj.service.user.domain.user.entity.po.User;
import cn.xdzn.oj.service.user.domain.user.repository.UserAcProblemRepository;
import cn.xdzn.oj.service.user.domain.user.service.UserRoleDomainService;
import cn.xdzn.oj.service.user.domain.user.service.UserDomainService;
import cn.xdzn.oj.service.user.interfaces.dto.LoginParamDTO;
import cn.xdzn.oj.service.user.interfaces.dto.UserDTO;
import cn.xdzn.oj.service.user.interfaces.dto.UserInfo;
import cn.xdzn.oj.service.user.infrastructure.dao.UserDao;
import cn.xdzn.oj.service.user.domain.user.entity.po.UserRole;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户服务实现类
 *
 * @author HeXin
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-03-08 21:08:14
 * @date 2024/03/08
 */
@Service
@RequiredArgsConstructor
public class UserDomainServiceImpl extends ServiceImpl<UserDao, User>
        implements UserDomainService {

    private final UserAcProblemRepository userAcProblemRepository;


    @Resource
    private RedisUtil redisUtil;

    @Resource
    private EmailUtils emailUtils;

    @Resource
    private RoleDomainService roleService;

    @Resource
    private UserRoleDomainService userRoleService;

    @Override
    public Boolean regist(UserDTO dto, String code) {
        // 从缓存中获取验证码
        if(redisUtil.getTime(RedisConstants.EMAIL_CODE.getKey() + dto.getEmail())==0){
            throw new CustomException(CodeEnum.CODE_EXPIRED);
        }
        if(!code.equals(redisUtil.getObject(RedisConstants.EMAIL_CODE.getKey() + dto.getEmail()).toString())){
            throw new CustomException(CodeEnum.CODE_ERROR);
        }
        Long count = lambdaQuery().eq(User::getUsername, dto.getUsername()).count();
        if(count > 0) {
            throw new CustomException(CodeEnum.USERNAME_EXIST);
        }
        User user = dto.toPo(User.class);
        user.setPassword(PasswordUtils.encrypt(user.getPassword()));
        return save(user);
    }

    @Override
    public String login(LoginParamDTO param) {
        String username = param.getUsername();
        String password = param.getPassword();
        User user = getUserByName(username);
        if(user == null){
            user = getUserByEmail(username);
            if(user == null){
                throw new CustomException(CodeEnum.USER_NOT_FOUND);
            }
        }
        if(!PasswordUtils.match(password,user.getPassword())){
            throw new CustomException(CodeEnum.PASSWORD_ERROR);
        }
        StpUtil.login(user.getUid());
        redisUtil.set(RedisConstants.USER_TOKEN, String.valueOf(user.getUid()),StpUtil.getTokenValue());
        redisUtil.set(RedisConstants.USER,String.valueOf(user.getUid()), JSON.toJSONString(user));
        return StpUtil.getTokenValue();
    }

    @Override
    public User getUserByName(String username) {
        return lambdaQuery().select(User::getUid,User::getUsername,User::getPassword,User::getType,User::getAvatar).eq(User::getUsername,username).one();
    }
    @Override
    public Boolean bindRole(Long userId, Long roleId) {
        UserRole userRole = new UserRole().setUserId(userId).setRoleId(roleId);
        return userRoleService.save(userRole);
    }

    @Override
    public Boolean unbindRole(Long userId, Long roleId) {
        return userRoleService.unbind(userId,roleId);
    }

    @Override
    public User appendInfo(User user) {
        List<Long> roleIdsByUserId = userRoleService.getRoleIdsByUserId(user.getUid());
        List<String> roleNamesByUserId = userRoleService.getRoleNamesByUserId(user.getUid());
        return user.setRoleIds(roleIdsByUserId).setRoleNames(roleNamesByUserId);
    }

    @Override
    public void sendCode(String email) {
        EmailUtils.isValidEmail(email);
        String code = ValidateCodeUtils.generateValidateCodeUtils(6).toString();
        // 调用邮箱服务发送验证码
        emailUtils.sendMailMessage(email,code);
        // 将生成的验证码存入Redis
        redisUtil.set(RedisConstants.EMAIL_CODE,email,code);
    }

    @Override
    public UserInfo getUserInfo() {
        String id = StpUtil.getLoginIdAsString();
        List<String> roleNames = roleService.getRoleNameByUser(Long.parseLong(id));
        redisUtil.set(RedisConstants.USER_ROLE.getKey()+id,roleNames);
        User user =  redisUtil.get(RedisConstants.USER.getKey() + id,User.class);
        return new UserInfo().setName(user.getUsername())
                .setRoleNames(roleNames)
                .setId(id)
                .setAvatar(user.getAvatar());
    }

    @Override
    public Map<Integer,Integer> acNum(List<Long> list) {
        return userAcProblemRepository.acNum(list);
    }

    @Override
    public List<Long> getUserAc(Long uid) {
        return userAcProblemRepository.getUserAc(uid);
    }

    @Override
    public Map<Long, Integer> getProblemAcNum(List<Long> ids) {
        return userAcProblemRepository.getProblemAcNum(ids);
    }

    @Override
    public Map<Long, String> getUserName(List<Long> userIds) {
        return lambdaQuery().select(User::getUid,User::getUsername)
                .in(User::getUid,userIds)
                .list()
                .stream()
                .collect(Collectors.toMap(User::getUid,User::getUsername));
    }

    /**
     * 通过电子邮件获取用户
     *
     * @param email 电子邮件
     * @return {@link User}
     */
    private User getUserByEmail(String email){
        return lambdaQuery().select(User::getUid,User::getUsername,User::getPassword,User::getType)
                .eq(User::getEmail,email)
                .one();
    }
}




