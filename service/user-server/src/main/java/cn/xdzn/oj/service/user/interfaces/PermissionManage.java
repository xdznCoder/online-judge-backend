package cn.xdzn.oj.service.user.interfaces;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.service.user.application.PermissionService;
import cn.xdzn.oj.service.user.common.AuthUtils;
import cn.xdzn.oj.service.user.domain.permission.model.aggregates.PermissionDTO;
import cn.xdzn.oj.service.user.infrastructure.pojo.Permission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限管理
 *
 * @author Onism
 * @date 2024/10/17
 */
@Tag(name = "权限管理")
@RestController
@RequestMapping("/user/permission")
public class PermissionManage {

    @Resource
    private PermissionService permissionService;

    @SaCheckPermission("admin.permission.add")
    @Operation(summary = "增加权限",description = "权限：admin.permission.add")
    @PostMapping("/")
    public Result<Void> addPermission(@RequestBody PermissionDTO permission) {
        permissionService.save(permission.toPo(Permission.class));
        return Result.success();
    }

    @SaCheckPermission("admin.permission.delete")
    @Operation(summary = "删除权限", description = "根据id进行权限表的删除<br/>权限：admin.permission.delete")
    @DeleteMapping("/{id}")
    public Result<Void> delPermission(@Schema(description = "权限表里权限字段对应的id") @PathVariable Long id) {
        return Result.isSuccess(permissionService.removePermission(id));
    }

    @SaCheckPermission("admin.permission.update")
    @Operation(summary = "修改权限", description = "有些字段不需要的可以直接不传<br/>权限：admin.permission.update")
    @PutMapping("/")
    public Result<Void> updatePermission(@RequestBody PermissionDTO permission) {
        return Result.isSuccess(permissionService.updateById(permission.toPo(Permission.class)));
    }

    @SaCheckPermission("admin.permission.get")
    @Operation(summary = "查询权限", description = "查询所有权限(树形权限,用于权限配置时选择<br/>权限：admin.permission.get")
    @GetMapping("/")
    public Result<List<Permission>> selectPermission() {
        List<Permission> list = permissionService.lambdaQuery()
                .select(Permission::getParentId, Permission::getPermissionName, Permission::getKeyName, Permission::getId)
                .list();
        return Result.success(AuthUtils.createTree(list));
    }

    @SaCheckPermission("admin.permission")
    @Operation(summary = "级联权限懒加载", description = "表格数据渲染<br/>权限：admin.permission")
    @GetMapping("/lazyLoad/{parentId}")
    public Result<List<Permission>> lazyLoad(@PathVariable String parentId) {
        List<Permission> children = permissionService.getChildren(parentId);
        return Result.success(children);
    }

    @Operation(summary = "根据id查询", description = "用于数据回显")
    @GetMapping("/{id}")
    public Result<Permission> selectPermissionById(@PathVariable Long id) {
        return Result.success(permissionService.getById(id));
    }

    @Operation(summary = "根据角色查询权限", description = "只会查询到id,用于数据回显")
    @GetMapping("/role/{roleId}")
    public Result<List<Long>> selectPermissionByRole(@PathVariable Long roleId) {
        List<Long> permissionTreeByRole = permissionService.getPermissionByRole(roleId);
        return Result.success(permissionTreeByRole);
    }
}
