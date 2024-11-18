package cn.xdzn.oj.service.problem.interfaces.dto;

import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProblemSimpleDTO {
    private Long id;

    private String problemId;

    private String title;
    public static ProblemSimpleDTO toDTO(Problem problem) {
        return new ProblemSimpleDTO()
                .setId(problem.getId())
                .setProblemId(problem.getProblemId())
                .setTitle(problem.getTitle());
    }
}
