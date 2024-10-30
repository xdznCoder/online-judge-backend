package cn.xdzn.oj.service.problem.domain.problem.service.impl;

import cn.xdzn.oj.service.problem.domain.problem.entity.po.ProblemTag;
import cn.xdzn.oj.service.problem.domain.problem.entity.vo.ProblemTagVO;
import cn.xdzn.oj.service.problem.domain.problem.repository.ProblemTagRepository;
import cn.xdzn.oj.service.problem.domain.problem.service.ProblemTagDomainService;
import cn.xdzn.oj.service.problem.infrastructure.dao.ProblemTagDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProblemTagDomainServiceImpl extends ServiceImpl<ProblemTagDao, ProblemTag> implements ProblemTagDomainService {
    private final ProblemTagRepository problemTagRepository;
    @Override
    public Map<Long, List<String>> getListTagNames(List<Long> ids) {
        // 查询 problem_tag 和 tag 表
        List<ProblemTagVO> problemTags = problemTagRepository.findTagsByProblemIds(ids);
        // 将结果转换为 Map<Long, List<String>>
        return problemTags.stream()
                .collect(Collectors.groupingBy(
                        ProblemTagVO::getPid, // 按问题 ID 分组
                        Collectors.mapping(ProblemTagVO::getTagName, Collectors.toList()) // 收集标签名
                ));
    }
}
