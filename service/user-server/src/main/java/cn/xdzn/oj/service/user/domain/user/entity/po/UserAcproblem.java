package cn.xdzn.oj.service.user.domain.user.entity.po;

import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * @TableName user_acproblem
 */
@TableName(value ="user_acproblem")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserAcproblem extends BaseEntity implements Serializable {
    private Long id;

    private String uid;

    private Long pid;

    private Long submitId;

    private Integer tid;
    @Serial
    private static final long serialVersionUID = 1L;
    
}