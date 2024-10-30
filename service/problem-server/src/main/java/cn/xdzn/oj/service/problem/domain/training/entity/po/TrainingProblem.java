package cn.xdzn.oj.service.problem.domain.training.entity.po;

import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * @TableName training_problem
 */
@TableName(value ="training_problem")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TrainingProblem extends BaseEntity implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long tid;

    private Long pid;

    private Integer rank;

    private String displayId;

    @Serial
    private static final long serialVersionUID = 1L;

}