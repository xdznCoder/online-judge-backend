package cn.xdzn.oj.service.problem.domain.Training.entity.po;

import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * @TableName training_category
 */
@TableName(value ="training_category")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
//new
public class TrainingCategory extends BaseEntity implements Serializable {
    private Long id;

    private Long tid;

    private Long cid;
    @Serial
    private static final long serialVersionUID = 1L;
}