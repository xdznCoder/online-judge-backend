package cn.xdzn.oj.service.problem.infrastructure.repository;

import cn.xdzn.oj.service.problem.domain.problem.repository.ProblemRepository;
import cn.xdzn.oj.service.problem.infrastructure.dao.ProblemTagDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProblemRepositoryImpl implements ProblemRepository {

    private final ProblemTagDao problemTagMapper;
    @Override
    public List<Integer> findProblemsByTagIds(List<Integer> tagIds) {
        return problemTagMapper.selectByTagIds(tagIds);
    }
}
