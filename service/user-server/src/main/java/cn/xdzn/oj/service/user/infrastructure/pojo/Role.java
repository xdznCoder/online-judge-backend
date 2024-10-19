package cn.xdzn.oj.service.user.infrastructure.pojo;

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
import java.util.Collections;
import java.util.List;

/**
 * 角色
 *
 * @author HeXin
 * @date 2024/03/08
 */
@TableName(value = "`role`")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity implements Serializable {
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
     * 角色名称
     */
    @TableField(value = "role_name")
    @Schema(description = "角色名称")
    private String roleName;
    /**
     * 角色关键词(权限认证字段)
     */
    @TableField(value = "role_key")
    @Schema(description = "角色关键词(权限认证字段)")
    private String roleKey;
    /**
     * 权限
     *
     * @see List <Long>
     */
    @Schema(description = "角色对应权限")
    @TableField(exist = false)
    private List<Long> permissions = Collections.emptyList();
    /**
     * 权限
     *
     * @see List<Long>
     */
    @Schema(description = "权限名称")
    @TableField(exist = false)
    private List<String> permissionNames = Collections.emptyList();
}