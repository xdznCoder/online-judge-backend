package cn.xdzn.oj.service.system.domain.notice.entity.po;

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
 * @TableName system_notice_user
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="system_notice_user")
@Data
@Accessors(chain = true)
public class SystemNoticeUser extends BaseEntity implements Serializable {
    private Long id;

    private Long noticeId;

    private Long userId;

    private Integer isRead;

    private Date gmtCreate;

    private Date gmtModified;

    private Long createBy;

    private Long updateBy;

    private Integer isDeleted;
    @Serial
    private static final long serialVersionUID = 1L;
}