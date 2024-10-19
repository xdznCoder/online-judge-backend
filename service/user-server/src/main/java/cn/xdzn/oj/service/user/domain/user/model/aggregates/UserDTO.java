package cn.xdzn.oj.service.user.domain.user.model.aggregates;


import cn.xdzn.oj.common.entity.ConverEntity;
import cn.xdzn.oj.service.user.infrastructure.pojo.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户dto
 *
 * @author HeXin
 * @date 2024/03/08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class UserDTO extends ConverEntity<User> {
    private Long id;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "昵称")
    private String nickname;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "用户类型, 0：学生 1：高校 2：企业 ")
    private Integer type;
    @Schema(description = "用户状态(0：禁用 1：启用，默认为1)")
    private Integer state;
}