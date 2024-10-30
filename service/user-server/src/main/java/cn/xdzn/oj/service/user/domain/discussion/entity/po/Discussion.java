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
 * @TableName discussion
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="discussion")
@Data
@Accessors(chain = true)
public class Discussion extends BaseEntity implements Serializable {
    private Integer id;

    private Integer discussionType;

    private String title;

    private String description;

    private String content;

    private String pid;

    private String uid;

    private String author;

    private String avatar;

    private String role;

    private Integer viewNum;

    private Integer likeNum;

    private Integer topPriority;

    private Integer commentNum;

    private Integer status;

    private Long gid;
    @Serial
    private static final long serialVersionUID = 1L;
}