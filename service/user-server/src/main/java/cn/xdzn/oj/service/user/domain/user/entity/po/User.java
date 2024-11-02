package cn.xdzn.oj.service.user.domain.user.entity.po;

import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @TableName user
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="user")
@Data
@Accessors(chain = true)
public class User extends BaseEntity implements Serializable {
    private Long uid;

    private String username;

    private String password;

    private String nickname;

    private String school;

    private String number;

    private String realname;

    private String gender;

    private String email;

    private String avatar;

    private String signature;

    private String titleName;

    private String titleColor;

    private Integer status;

    private Integer type;

    @TableField(exist = false)
    @Schema(description = "角色名称")
    private List<String> roleNames;
    @TableField(exist = false)
    @Schema(description = "角色id")
    private List<Long> roleIds;

    @Serial
    private static final long serialVersionUID = 1L;
}