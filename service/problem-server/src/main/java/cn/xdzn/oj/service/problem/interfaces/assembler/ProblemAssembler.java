package cn.xdzn.oj.service.problem.interfaces.assembler;

import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;
import cn.xdzn.oj.service.problem.interfaces.dto.NewProblemDTO;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemDTO;

public class ProblemAssembler {
    private ProblemAssembler(){}

    public static ProblemDTO toDTO(Problem problem)
    {
        return new ProblemDTO()
                .setId(problem.getId())
                .setProblemId(problem.getProblemId())
                .setTitle(problem.getTitle())
                .setGmtCreate(problem.getGmtCreate())
                .setDifficulty(problem.getDifficulty())
                .setAuthor(problem.getAuthor())
                .setGid(problem.getGid())
                .setAuth(problem.getAuth())
                .setCreateBy(problem.getCreateBy())
                .setModifiedUser(problem.getModifiedUser())
                .setUpdateBy(problem.getUpdateBy());
    }

    public static NewProblemDTO toNewProblemDTO(Problem problem){
        return new NewProblemDTO()
                .setId(problem.getId())
                .setProblemId(problem.getProblemId())
                .setTitle(problem.getTitle())
                .setGmtCreate(problem.getGmtCreate())
                .setDifficulty(problem.getDifficulty());
    }
}
