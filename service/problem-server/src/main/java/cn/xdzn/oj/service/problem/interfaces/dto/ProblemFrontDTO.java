package cn.xdzn.oj.service.problem.interfaces.dto;

import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ProblemFrontDTO {
    private Long id;

    private String problemId;

    private String title;

    private Integer difficulty;

    private Integer submitNum;

    private Integer acNum;

    private Double acRate;

    private List<String> tagNames;

    private Boolean isAc;

    public static ProblemFrontDTO toDTO(Problem problem){
        return new ProblemFrontDTO()
                .setId(problem.getId())
                .setProblemId(problem.getProblemId())
                .setTitle(problem.getTitle())
                .setDifficulty(problem.getDifficulty());
    }
}
