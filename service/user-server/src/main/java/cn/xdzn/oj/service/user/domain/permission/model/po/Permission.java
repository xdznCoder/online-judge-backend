package cn.xdzn.oj.service.user.domain.permission.model.po;

import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 权限表
 *
 * @author HeXin
 * @date 2024/03/08
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "`permission`")
@Data
@Accessors(chain = true)
public class Permission extends BaseEntity implements Serializable {

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "id")
    private Long id;
    /**
     * 权限名称
     */
    @Schema(description = "权限名称")
    @TableField(value = "permission_name")
    private String permissionName;
    /**
     * 权限关键词(权限认证使用此字段)
     */
    @Schema(description = "权限关键词(权限认证使用此字段)")
    @TableField(value = "key_name")
    private String keyName;
    /**
     * 路由路径
     */
    @Schema(description = "路由路径")
    @TableField(value = "`path`")
    private String path;
    /**
     * 参数
     */
    @Schema(description = "参数")
    @TableField(value = "`perms`")
    private String perms;
    /**
     * 路由组件
     */
    @Schema(description = "路由组件")
    @TableField(value = "`component`")
    private String component;
    /**
     * 父级权限id
     */
    @Schema(description = "父级权限id")
    @TableField(value = "parent_id")
    private Long parentId;
    /**
     * 图标
     */
    @Schema(description = "图标")
    @TableField(value = "`icon`")
    private String icon;

    @Schema(description = "是否存在子级权限")
    @TableField(exist = false)
    private Boolean hasChildren = false;
    /**
     * 子级权限
     *
     * @see List <Permission>
     */
    @Schema(description = "子级权限")
    @TableField(exist = false)
    private List<Permission> children;
}