package cn.xdzn.oj.service.user.domain.message.entity.po;

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
 * @TableName user_msg
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="user_msg")
@Data
@Accessors(chain = true)
public class UserMsg extends BaseEntity implements Serializable {
    private Long id;

    private Integer action;

    private Integer sourceId;

    private String sourceContent;

    private Integer quoteId;

    private Integer quoteType;

    private String url;

    private Integer state;

    private Long senderId;

    private Long recipientId;
    @Serial
    private static final long serialVersionUID = 1L;
}