package cn.xdzn.oj.service.user.interfaces.manage;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.xdzn.oj.common.PageInfo;
import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.service.user.domain.role.service.RoleDomainService;
import cn.xdzn.oj.service.user.interfaces.dto.RoleDTO;
import cn.xdzn.oj.service.user.domain.role.model.po.Role;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理
 *
 * @author Onism
 * @date 2024/10/17
 */
@Tag(name = "角色管理")
@RestController
@RequestMapping("/user/role")
public class RoleManage {

    @Resource
    private RoleDomainService roleService;

    @SaCheckPermission("admin.role.add")
    @Operation(summary = "增加角色", description = "permissions字段用于传输需要绑定的权限id\n权限：admin.role.add")
    @PostMapping("/")
    public Result<Void> addRole(@RequestBody RoleDTO dto) {
        boolean flag = roleService.saveRole(dto.toPo(Role.class));
        return Result.isSuccess(flag);
    }

    @SaCheckPermission("admin.role.delete")
    @Operation(summary = "删除角色",description = "权限：admin.role.delete")
    @DeleteMapping("/{id}")
    public Result<Void> delRole(@Schema(description = "角色表里权限字段对应的id") @PathVariable Long id) {
        roleService.removeRole(id);
        return Result.success();
    }

    @SaCheckPermission("admin.role.update")
    @Operation(summary = "修改角色", description = "permissions字段用于传输需要重新绑定的权限id(完全覆盖,不会在原有基础上增加或者删除)\n权限：admin.role.update")
    @PutMapping("/")
    public Result<Void> updateRole(@RequestBody RoleDTO dto) {
        boolean flag = roleService.updateRole(dto.toPo(Role.class));
        return Result.isSuccess(flag);
    }

    @SaCheckPermission("admin.role.get")
    @Operation(summary = "查询角色", description = "查询所有\n权限：admin.role.get")
    @GetMapping("/")
    public Result<List<Role>> selectRole() {
        return Result.success(roleService.list());
    }

    @SaCheckPermission("admin.role.get")
    @Operation(summary = "条件分页查询角色", description = "条件分页查询")
    @GetMapping("/page")
    public Result<PageInfo<Role>> selectRolePage(@RequestParam(required = false, defaultValue = "1") Long pageNum,
                                                 @RequestParam(required = false, defaultValue = "10") Long pageSize,
                                                 @RequestParam(required = false, defaultValue = "") String key) {
        IPage<Role> rolePage = roleService.lambdaQuery()
                .like(StringUtils.isNotBlank(key), Role::getRoleName, key)
                .page(new Page<>(pageNum, pageSize))
                .convert(roleService::appendPermission);
        return Result.page(rolePage);
    }

    @Operation(summary = "绑定权限",description = "为角色绑定权限")
    @PostMapping("/bind/{roleId}/{permissionId}")
    @SaCheckPermission("admin.role.bind")
    public Result<Void> bind(@PathVariable Long roleId,@PathVariable Long permissionId){
        return Result.isSuccess(roleService.bindPermission(roleId,permissionId));
    }

    @Operation(summary = "解除绑定")
    @SaCheckPermission("admin.role.unbind")
    @DeleteMapping("/unbind/{roleId}/{permissionId}")
    public Result<Void> unbind(@PathVariable Long roleId,@PathVariable Long permissionId){
        return Result.isSuccess(roleService.unbindPermission(roleId,permissionId));
    }

}
