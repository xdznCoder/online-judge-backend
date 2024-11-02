package cn.xdzn.oj.service.user.domain.announcement.entity.po;

import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @TableName announcement
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="announcement")
@Data
public class Announcement extends BaseEntity implements Serializable {
    private Long id;

    private String title;

    private String content;

    private Long uid;

    private Integer status;

    private Long gid;
    @Serial
    private static final long serialVersionUID = 1L;
}