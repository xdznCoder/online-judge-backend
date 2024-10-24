package cn.xdzn.oj.service.problem.domain.problem.entity.po;
import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * @TableName problem_tag
 */
@TableName(value ="problem_tag")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ProblemTag extends BaseEntity implements Serializable {
    private Long id;

    private Long pid;

    private Long tid;

    @Serial
    private static final long serialVersionUID = 1L;

}