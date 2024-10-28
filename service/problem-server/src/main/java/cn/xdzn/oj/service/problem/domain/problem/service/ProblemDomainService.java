package cn.xdzn.oj.service.problem.domain.problem.service;

import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemFrontDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface ProblemDomainService extends IService<Problem> {
    IPage<Problem> selectPage(Long pageNum, Long pageSize, String key, List<Integer> tagIds, Integer type);

    List<ProblemFrontDTO> fill(List<ProblemFrontDTO> records, Map<Long, Integer> submitNumMap, Map<Long, Integer> acNumMap, Map<Long, List<String>> tagNamesMap,List<Long> acList);
}
