package cn.xdzn.oj.service.problem.interfaces.dto;

import cn.xdzn.oj.common.entity.ConverEntity;
import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ProblemDTO extends ConverEntity<Problem> {
    private Long id;

    private String problemId;

    private String title;

    private Integer difficulty;

    private Long gid;

    private Date gmtCreate;

    private Integer auth;

    private Long createBy;

    private Long updateBy;

    private String author;

    private String modifiedUser;
}
