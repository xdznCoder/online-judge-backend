package cn.xdzn.oj.service.user.interfaces.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 登录参数
 *
 * @author HeXin
 * @date 2024/03/08
 */
@Data
public class LoginParamDTO {
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "密码")
    private String password;
}
