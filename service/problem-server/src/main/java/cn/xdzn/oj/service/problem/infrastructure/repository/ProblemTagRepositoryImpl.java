package cn.xdzn.oj.service.problem.infrastructure.repository;

import cn.xdzn.oj.service.problem.domain.problem.repository.ProblemTagRepository;
import cn.xdzn.oj.service.problem.infrastructure.dao.ProblemTagDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProblemTagRepositoryImpl implements ProblemTagRepository {

    private final ProblemTagDao problemTagMapper;
    @Override
    public List<Integer> findProblemsByTagIds(List<Integer> tagIds) {
        return problemTagMapper.selectByTagIds(tagIds);
    }
}
