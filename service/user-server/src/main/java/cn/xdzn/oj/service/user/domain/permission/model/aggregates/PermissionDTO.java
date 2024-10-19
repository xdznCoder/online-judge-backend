package cn.xdzn.oj.service.user.domain.permission.model.aggregates;


import cn.xdzn.oj.common.entity.ConverEntity;
import cn.xdzn.oj.service.user.infrastructure.pojo.Permission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 权限dto
 *
 * @author HeXin
 * @date 2024/03/08
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class PermissionDTO extends ConverEntity<Permission> {
    private Long id;
    /**
     * 权限名称
     */
    @Schema(description = "权限名称")
    private String permissionName;
    /**
     * 权限关键词(权限认证使用此字段)
     */
    @Schema(description = "权限关键词(权限认证使用此字段)")
    private String keyName;
    /**
     * 路由路径
     */
    @Schema(description = "路由路径")
    private String path;
    /**
     * 参数
     */
    @Schema(description = "参数")
    private String perms;
    /**
     * 路由组件
     */
    @Schema(description = "路由组件")
    private String component;
    /**
     * 父级权限id
     */
    @Schema(description = "父级权限id")
    private Long parentId;
    /**
     * 图标
     */
    @Schema(description = "图标")
    private String icon;
    /**
     * 有子权限
     *
     * @see Boolean
     */
    @Schema(description = "是否存在子级权限")
    private Boolean hasChildren = false;
}
