package cn.xdzn.oj.service.problem.application;


import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.common.client.UserClient;
import cn.xdzn.oj.common.exception.CustomException;
import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;

import cn.xdzn.oj.service.problem.domain.problem.service.ProblemDomainService;
import cn.xdzn.oj.service.problem.domain.problem.service.ProblemTagDomainService;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemDTO;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemFrontDTO;
import cn.xdzn.oj.service.problem.domain.problem.entity.po.ProblemTag;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemSimpleDTO;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;


/**
 * 题目应用层
 * @author shelly
 * @date 2024/10/21 10:03
 */
@Service
@RequiredArgsConstructor
public class ProblemApplicationService {
    // 静态Random对象，只初始化一次
    private static final Random RANDOM = new Random();

    private final ProblemDomainService problemDomainService;
    private final ProblemTagDomainService problemTagDomainService;
    @Lazy
    private final UserClient userClient;

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

    public Problem random() {
        // 获取当前用户已AC的题目
        List<Long> list = userClient.getUserAc();
        List<Long> ids = problemDomainService
                .lambdaQuery()
                .select(Problem::getId)
                .notIn(Problem::getId, list)
                .list()
                .stream()
                .map(Problem::getId)
                .toList();
        int size = ids.size();
        if(size == 0){
            throw new CustomException("无更多题目");
        }
        Long randomId = ids.get(RANDOM.nextInt(size));
        // 获取随机题目的详细信息并返回
        return problemDomainService.detail(randomId);
    }

    public IPage<ProblemFrontDTO> selectPage(Long pageNum, Long pageSize, String key, List<Integer> tagIds, Integer difficulty) {
        List<Long> problemsByTagIds = null;
        if(tagIds != null){
            problemsByTagIds = problemTagDomainService.lambdaQuery()
                    .select(ProblemTag::getPid)
                    .in(ProblemTag::getTid, tagIds)
                    .list()
                    .stream()
                    .map(ProblemTag::getPid)
                    .toList();
        }
        return problemDomainService.lambdaQuery()
                .select(Problem::getId, Problem::getTitle, Problem::getDifficulty,
                       Problem::getProblemId)
                .like(StringUtils.isNotBlank(key),Problem::getTitle, key)
                .eq(difficulty != null, Problem::getDifficulty, difficulty)
                .eq(Problem::getAuth, 1)
                .eq(Problem::getIsDeleted, 0)
                .in(Problem::getApplyPublicProgress, Arrays.asList(null,2))
                .in(problemsByTagIds != null, Problem::getId, problemsByTagIds)
                .page(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageNum, pageSize))
                .convert(ProblemFrontDTO::toDTO);
    }

    public Map<String, Long> queryMonitorInfo() {
        Map<String, Long> map = new HashMap<>();
        Long problemCount = problemDomainService.lambdaQuery().eq(Problem::getIsDeleted, 0).count();
        map.put("problemCount", problemCount);
        //TODO 获取题目提交数量
        return map;
    }

    public IPage<ProblemSimpleDTO> searchList(Long pageNum, Long pageSize, String key) {
        return problemDomainService.lambdaQuery()
                .select(Problem::getId, Problem::getTitle, Problem::getProblemId)
                .like(StringUtils.isNotBlank(key), Problem::getTitle, key)
                .eq(Problem::getAuth, 1)
                .eq(Problem::getIsDeleted, 0)
                .in(Problem::getApplyPublicProgress, Arrays.asList(null,2))
                .page(new Page<>(pageNum, pageSize))
                .convert(ProblemSimpleDTO::toDTO);

    }
}
