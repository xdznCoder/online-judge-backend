package cn.xdzn.oj.service.problem.domain.problem.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;
import cn.xdzn.oj.service.problem.domain.problem.repository.ProblemTagRepository;
import cn.xdzn.oj.service.problem.domain.problem.service.ProblemDomainService;
import cn.xdzn.oj.service.problem.infrastructure.dao.ProblemDao;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemFrontDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProblemDomainServiceImpl extends ServiceImpl<ProblemDao, Problem> implements ProblemDomainService {

    private final ProblemTagRepository problemTagRepository;
    @Override
    public IPage<Problem> selectPage(Long pageNum, Long pageSize, String key, List<Integer> tagIds, Integer difficulty) {
        List<Integer> problemsByTagIds = problemTagRepository.findProblemsByTagIds(tagIds);
        return lambdaQuery()
                .in(Problem::getId, problemsByTagIds)
                .like(StringUtils.isNotBlank(key),Problem::getTitle, key)
                .eq(difficulty!=-1, Problem::getDifficulty, difficulty)
                .page(new Page<>(pageNum, pageSize));
    }

    @Override
    public List<ProblemFrontDTO> fill(List<ProblemFrontDTO> records, Map<Long, Integer> submitNumMap, Map<Long, Integer> acNumMap, Map<Long, List<String>> tagNamesMap,List<Long> acList) {
        for (ProblemFrontDTO dto : records) {
            Long problemId = dto.getId();
            dto.setSubmitNum(submitNumMap.get(problemId));
            dto.setAcNum(acNumMap.get(problemId));
            dto.setAcRate(dto.getAcNum() * 100.0 / dto.getSubmitNum());
            dto.setTagNames(tagNamesMap.get(problemId));
            dto.setIsAc(acList.contains(problemId));
        }
        return records;
    }
}
