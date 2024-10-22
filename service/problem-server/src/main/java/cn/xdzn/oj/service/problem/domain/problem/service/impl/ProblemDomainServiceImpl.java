package cn.xdzn.oj.service.problem.domain.problem.service.impl;

import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;
import cn.xdzn.oj.service.problem.domain.problem.repository.ProblemRepository;
import cn.xdzn.oj.service.problem.domain.problem.service.ProblemDomainService;
import cn.xdzn.oj.service.problem.infrastructure.dao.ProblemDao;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemDomainServiceImpl extends ServiceImpl<ProblemDao, Problem> implements ProblemDomainService {

    private final ProblemRepository problemRepository;
    @Override
    public IPage<Problem> selectPage(Long pageNum, Long pageSize, String key, List<Integer> tagIds, Integer difficulty) {
        List<Integer> problemsByTagIds = problemRepository.findProblemsByTagIds(tagIds);
        return lambdaQuery()
                .in(Problem::getId, problemsByTagIds)
                .like(StringUtils.isNotBlank(key),Problem::getTitle, key)
                .eq(difficulty!=-1, Problem::getDifficulty, difficulty)
                .page(new Page<>(pageNum, pageSize));
    }


}
