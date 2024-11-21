package cn.xdzn.oj.service.user.domain.discussion.entity.po;

import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @TableName discussion_comment_like
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="discussion_comment_like")
@Data
@Accessors(chain = true)
public class DiscussionCommentLike extends BaseEntity implements Serializable {
    private Integer id;

    private Long uid;

    private Integer cid;
    @Serial
    private static final long serialVersionUID = 1L;
}