package cn.xdzn.oj.service.problem.infrastructure.repository;

import cn.xdzn.oj.service.problem.domain.Training.entity.vo.TrainingNumVO;
import cn.xdzn.oj.service.problem.domain.Training.repository.TrainingProblemRepository;
import cn.xdzn.oj.service.problem.infrastructure.dao.TrainingProblemDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingProblemRepositoryImpl implements TrainingProblemRepository {

    private final TrainingProblemDao trainingProblemDao;

    @Override
    public List<TrainingNumVO> getProblemNum(List<Long> list) {
        return trainingProblemDao.getProblemNum(list);
    }

    @Override
    public List<Integer> getProblemIds(Long id) {
        return trainingProblemDao.getProblemIds(id);
    }

    @Override
    public void addProblem(Long tid, Long pid,String displayId) {
        trainingProblemDao.addProblem(tid, pid, displayId);
    }

    @Override
    public void deleteProblem(Long id) {
        trainingProblemDao.deleteById(id);
    }


}
