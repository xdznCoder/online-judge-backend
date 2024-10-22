package cn.xdzn.oj.service.problem.domain.problem.service;

import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ProblemDomainService extends IService<Problem> {
    IPage<Problem> selectPage(Long pageNum, Long pageSize, String key, List<Integer> tagIds, Integer type);
}
