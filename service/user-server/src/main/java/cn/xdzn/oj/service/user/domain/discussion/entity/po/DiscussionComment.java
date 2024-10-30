package cn.xdzn.oj.service.user.domain.discussion.entity.po;

import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @TableName discussion_comment
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="discussion_comment")
@Data
public class DiscussionComment extends BaseEntity implements Serializable {
    private Integer id;

    private Integer did;

    private String content;

    private String fromUid;

    private String fromName;

    private String fromAvatar;

    private String fromRole;

    private Integer likeNum;

    private Integer status;
    @Serial
    private static final long serialVersionUID = 1L;
}