package cn.xdzn.oj.service.problem.interfaces.dto;

import cn.xdzn.oj.common.entity.ConverEntity;
import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ProblemDTO extends ConverEntity<Problem> {

}
