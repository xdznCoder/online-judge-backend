package cn.xdzn.oj.service.user.domain.user.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户vo
 *
 * @author HeXin
 * @date 2024/03/08
 */
@Data
@Accessors(chain = true)
public class UserVO {
    private Long uid;
    private String avatar;
    private String username;
    private String nickname;
}
