package cn.xdzn.oj.service.problem.domain.Training.repository;

import cn.xdzn.oj.service.problem.domain.Training.entity.vo.TrainingNumVO;
import java.util.List;

public interface TrainingProblemRepository {
    List<TrainingNumVO> getProblemNum(List<Long> list);


    List<Integer> getProblemIds(Long id);

    void addProblem(Long tid, Long pid,String displayId);

    void deleteProblem(Long id);
}
