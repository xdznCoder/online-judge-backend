package cn.xdzn.oj.service.problem.interfaces.dto;

import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProblemApplyDTO {
    private Long id;

    private String problemId;

    private String title;

    private String author;

    private Long gid;

    private Integer type;

    private Integer applyPublicProgress;

    private Long createBy;

    public static  ProblemApplyDTO toDTO(Problem problem){
        return new ProblemApplyDTO()
                .setId(problem.getId())
                .setProblemId(problem.getProblemId())
                .setTitle(problem.getTitle())
                .setAuthor(problem.getAuthor())
                .setGid(problem.getGid())
                .setType(problem.getType())
                .setApplyPublicProgress(problem.getApplyPublicProgress())
                .setCreateBy(problem.getCreateBy());
    }
}
