package cn.xdzn.oj.service.problem.domain.problem.repository;

import cn.xdzn.oj.service.problem.domain.problem.entity.vo.ProblemTagVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 仓储接口
 */
@Repository
public interface ProblemTagRepository {
    // 根据 tagIds 查找关联的题目
    List<Integer> findProblemsByTagIds(List<Integer> tagIds);

    List<ProblemTagVO> findTagsByProblemIds(List<Long> ids);
}
