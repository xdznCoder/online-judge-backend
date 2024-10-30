package cn.xdzn.oj.service.user.domain.discussion.entity.po;

import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @TableName discussion_report
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="discussion_report")
@Data
public class DiscussionReport extends BaseEntity implements Serializable {
    private Integer id;

    private Integer did;

    private String reporter;

    private String content;

    private Integer status;
    @Serial
    private static final long serialVersionUID = 1L;
}