package cn.xdzn.oj.service.system.domain.notice.entity.po;

import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @TableName system_notice
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="system_notice")
@Data
@Accessors(chain = true)
public class SystemNotice extends BaseEntity implements Serializable {
    private Long id;

    private String title;

    private String content;

    private String type;

    private Integer state;

    private String admin;

    private Date gmtCreate;

    private Date gmtModified;

    private Long createBy;

    private Long updateBy;

    private Integer isDeleted;
    @Serial
    private static final long serialVersionUID = 1L;
}