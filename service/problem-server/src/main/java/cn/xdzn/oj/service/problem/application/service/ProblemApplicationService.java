package cn.xdzn.oj.service.problem.application.service;


import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;

import cn.xdzn.oj.service.problem.domain.problem.service.ProblemDomainService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 题目应用层
 * @author shelly
 * @date 2024/10/21 10:03
 */
@Service
@RequiredArgsConstructor
public class ProblemApplicationService {

    private final ProblemDomainService problemDomainService;

    public IPage<Problem> selectPage(Long pageNum, Long pageSize, String key, List<Integer> tagIds, Integer type) {
        return problemDomainService.selectPage(pageNum, pageSize, key, tagIds, type);
    }
}
