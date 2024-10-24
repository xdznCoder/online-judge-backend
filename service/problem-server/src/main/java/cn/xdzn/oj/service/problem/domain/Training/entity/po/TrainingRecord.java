package cn.xdzn.oj.service.problem.domain.Training.entity.po;

import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @TableName training_record
 */
@TableName(value ="training_record")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TrainingRecord extends BaseEntity implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long tid;

    private Long tpid;

    private Long pid;

    private Long submitId;

    private Date gmtCreate;

    private Date gmtModified;

    private Long createBy;

    private Long updateBy;

    private Integer isDeleted;

    @Serial
    private static final long serialVersionUID = 1L;
}