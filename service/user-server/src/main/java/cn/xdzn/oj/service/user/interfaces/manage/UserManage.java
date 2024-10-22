package cn.xdzn.oj.service.user.interfaces.manage;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import cn.xdzn.oj.common.PageInfo;
import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.common.constants.CodeEnum;
import cn.xdzn.oj.common.controller.BaseController;
import cn.xdzn.oj.common.util.PasswordUtils;
import cn.xdzn.oj.service.user.domain.user.service.UserDomainService;
import cn.xdzn.oj.service.user.interfaces.dto.LoginParamDTO;
import cn.xdzn.oj.service.user.interfaces.dto.PasswordDTO;
import cn.xdzn.oj.service.user.interfaces.dto.UserDTO;
import cn.xdzn.oj.service.user.interfaces.dto.UserInfo;
import cn.xdzn.oj.service.user.domain.user.model.vo.UserVO;
import cn.xdzn.oj.service.user.infrastructure.feign.UserClient;
import cn.xdzn.oj.service.user.domain.user.model.po.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author Onism
 * @date 2024/10/17
 */
@Tag(name = "用户模块")
@SaCheckLogin
@RestController
@RequestMapping("/user")
public class UserManage  extends BaseController<UserDomainService, User, UserDTO, Long> implements UserClient {

    
    @Override
    @PostMapping()
    public Result<Void> save(@RequestBody UserDTO instance) {
        return super.save(instance.setPassword(PasswordUtils.encrypt(instance.getPassword())));
    }

    @Operation(summary = "登录")
    @SaIgnore
    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginParamDTO param){
        return Result.success(service.login(param));
    }

    @Operation(summary = "退出登录")
    @SaIgnore
    @GetMapping("/logout")
    public Result<String> logout(){
        StpUtil.logout(StpUtil.getLoginId());
        return Result.success();
    }

    @Operation(summary = "发送验证码")
    @SaIgnore
    @GetMapping("/sendCode")
    public Result<String> sendCode(@RequestParam("email") String email){
        service.sendCode(email);
        return Result.success();
    }

    @Operation(summary = "注册账号")
    @SaIgnore
    @PostMapping("/regist")
    public Result<String> regist(@RequestBody UserDTO dto, @RequestParam("code") String code){
        return Result.isSuccess(service.regist(dto,code));
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/info")
    public Result<UserInfo> getUserInfo(){
        UserInfo info = service.getUserInfo();
        return Result.success(info);
    }

    @Operation(summary = "绑定角色")
    @SaCheckPermission("admin.user.add")
    @PostMapping("/bind/{userId}/{roleId}")
    public Result<Void> bind(@PathVariable Long userId, @PathVariable Long roleId){
        return Result.isSuccess(service.bindRole(userId,roleId));
    }

    @Operation(summary = "解除角色绑定")
    @SaCheckPermission("admin.user.delete")
    @DeleteMapping("/unbind/{userId}/{roleId}")
    public Result<Void> unbind(@PathVariable Long userId, @PathVariable Long roleId){
        return Result.isSuccess(service.unbindRole(userId,roleId));
    }

    @Override
    @Operation(summary="根据id获取用户信息（后台）")
    @SaCheckPermission("admin.user.get")
    @GetMapping("/{id}")
    public Result<User> get(@PathVariable  Long id) {
        return super.get(id);
    }

    @Operation(summary="根据id获取用户信息（前台）")
    @SaCheckPermission("user.get")
    @GetMapping("/front/{id}")
    public Result<UserVO> getById(@PathVariable Long id){
        User user = service.getById(id);
        UserVO vo = new UserVO().setAvatar(user.getAvatar())
                .setUid(user.getId())
                .setNickname(user.getNickname())
                .setUsername(user.getUsername());
        return success(vo);
    }

    @Operation(summary = "修改密码")
    @PutMapping("/update/password")
    public Result<String> updatePassword(@RequestBody PasswordDTO passwordDTO){
        User user = service.getById(StpUtil.getLoginIdAsString());
        if(!PasswordUtils.match(passwordDTO.oldPassword(),user.getPassword())){
            return Result.fail(CodeEnum.PASSWORD_ERROR);
        }
        if(!passwordDTO.confirmPassword().equals(passwordDTO.newPassword())){
            return  Result.fail(CodeEnum.PASSWORD_NOT_MATCH);
        }
        return Result.isSuccess(service.updateById(user.setPassword(PasswordUtils.encrypt(passwordDTO.newPassword()))));
    }

    @Operation(summary = "重置密码(后台)")
    @SaCheckPermission("admin.user.update")
    @PatchMapping("/reset/password")
    public Result<Void> resetPassword (@RequestParam Long uid,@RequestParam String password){
        return Result.isSuccess(service.lambdaUpdate().eq(User::getId,uid)
                .set(User::getPassword,PasswordUtils.encrypt(password))
                .update());
    }

    @Operation(summary = "分页查询")
    @SaCheckPermission("admin.user.get")
    @GetMapping("/page")
    public Result<PageInfo<User>> page(@RequestParam(required = false, defaultValue = "1") Long pageNum,
                                       @RequestParam(required = false, defaultValue = "10") Long pageSize,
                                       @RequestParam(required = false) String key,
                                       @RequestParam(required = false, defaultValue = "-1") Integer type,
                                       @RequestParam(required = false, defaultValue = "0") Integer withRole) {
        IPage<User> userPage = service.lambdaQuery().eq(!type.equals(-1), User::getType, type).like(StringUtils.isNotBlank(key), User::getNickname, key).page(new Page<>(pageNum, pageSize));
        if (!withRole.equals(0)) {
            userPage = userPage.convert(service::appendInfo);
        }
        return Result.page(userPage);
    }

    @Operation(summary = "修改用户")
    @PutMapping("/update")
    public Result<Void> update(@RequestBody User user){
        User u = service.getById(user.getId());
        if(!PasswordUtils.match(user.getPassword(), u.getPassword())){
            user.setPassword(PasswordUtils.encrypt(user.getPassword()));
        }
        return Result.isSuccess(service.updateById(user));
    }
    @Override
    protected Class<User> createInstance() {
        return User.class;
    }
}
