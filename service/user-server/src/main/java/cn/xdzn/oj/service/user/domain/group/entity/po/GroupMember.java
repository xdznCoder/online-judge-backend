package cn.xdzn.oj.service.user.domain.group.entity.po;

import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @TableName group_member
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="group_member")
@Data
@Accessors(chain = true)
public class GroupMember extends BaseEntity implements Serializable {
    private Long id;

    private Long gid;

    private String uid;

    private Integer auth;

    private String reason;

    @Serial
    private static final long serialVersionUID = 1L;
}