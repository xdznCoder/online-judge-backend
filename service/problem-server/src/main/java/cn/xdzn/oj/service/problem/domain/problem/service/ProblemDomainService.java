package cn.xdzn.oj.service.problem.domain.problem.service;

import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;
import cn.xdzn.oj.service.problem.interfaces.dto.NewProblemDTO;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemApplyDTO;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemDTO;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemFrontDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface ProblemDomainService extends IService<Problem> {

    List<ProblemFrontDTO> fill(List<ProblemFrontDTO> records, Map<Long, Integer> submitNumMap, Map<Long, Integer> acNumMap, Map<Long, List<String>> tagNamesMap,List<Long> acList);

    Problem detail(Long id);

    List<NewProblemDTO> newProblem();

    String getLastCode(Long pid, Long uid);

    Void updateCode(Long pid, Long uid, String code, String language);

    IPage<ProblemApplyDTO> getAllApply(Long pageNum, Long pageSize, String key, Long gid);

    IPage<ProblemDTO> backList(Long pageNum, Long pageSize, String key, Integer auth);
}
