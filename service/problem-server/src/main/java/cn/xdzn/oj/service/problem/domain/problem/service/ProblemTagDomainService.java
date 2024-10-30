package cn.xdzn.oj.service.problem.domain.problem.service;

import cn.xdzn.oj.service.problem.domain.problem.entity.po.ProblemTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface ProblemTagDomainService extends IService<ProblemTag> {
    Map<Long, List<String>> getListTagNames(List<Long> ids);
}
