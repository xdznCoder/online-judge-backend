package cn.xdzn.oj.service.problem.domain.problem.entity.po;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * @TableName problem_tag
 */
@TableName(value ="problem_tag")
@Data
public class ProblemTag implements Serializable {
    private Long id;

    private Long pid;

    private Long tid;

    private Date gmtCreate;

    private Date gmtModified;
    @Serial
    private static final long serialVersionUID = 1L;

}