package cn.xdzn.oj.service.user.domain.role.entity.po;

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

/**
 * 角色权限
 *
 * @author HeXin
 * @date 2024/03/08
 */
@TableName(value = "role_permission")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class RolePermission extends BaseEntity implements Serializable {
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
     * 角色id
     */
    @TableField(value = "role_id")
    @Schema(description = "角色id")
    private Long roleId;
    /**
     * 权限id
     */
    @TableField(value = "permission_id")
    @Schema(description = "权限id")
    private Long permissionId;
    /**
     * 删除id
     */
    @TableField(value = "deleted_id")
    @Schema(description = "删除id")
    private Long deletedId;
}