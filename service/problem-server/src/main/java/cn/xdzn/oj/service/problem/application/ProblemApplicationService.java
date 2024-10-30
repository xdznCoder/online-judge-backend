package cn.xdzn.oj.service.problem.application;


import cn.xdzn.oj.common.client.UserClient;
import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;

import cn.xdzn.oj.service.problem.domain.problem.service.ProblemDomainService;
import cn.xdzn.oj.service.problem.domain.problem.service.ProblemTagDomainService;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemFrontDTO;
import cn.xdzn.oj.service.problem.domain.problem.entity.vo.ProblemSubmitNumVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 题目应用层
 * @author shelly
 * @date 2024/10/21 10:03
 */
@Service
@RequiredArgsConstructor
public class ProblemApplicationService {

    private final ProblemDomainService problemDomainService;
    private final ProblemTagDomainService problemTagDomainService;
    private final UserClient userClient;

    public IPage<Problem> selectPage(Long pageNum, Long pageSize, String key, List<Integer> tagIds, Integer type) {
        return problemDomainService.selectPage(pageNum, pageSize, key, tagIds, type);
    }

    public List<ProblemFrontDTO> fillProblemInfo(List<ProblemFrontDTO> records) {
        // 获取题目id
        List<Long> ids = records.stream().map(ProblemFrontDTO::getId).toList();

//        // 获取每个问题的提交数量
//        Map<Long, Integer> submitNumMap = problemRepository.getSubmitNum(ids)
//                .stream().collect(Collectors.toMap(ProblemSubmitNumVO::getId, ProblemSubmitNumVO::getSubmitNum));

        // 获取每个问题的接受数量
        Map<Long, Integer> acNumMap = userClient.getProblemAcNum(ids);

        // 获取每个问题的标签名称
        Map<Long, List<String>> tagNamesMap = problemTagDomainService.getListTagNames(ids);

        //查找当前用户AC题目列表
        List<Long> acList = userClient.getUserAc();

        // 填充问题信息
        return problemDomainService.fill(records, null, acNumMap, tagNamesMap, acList);
    }
}
