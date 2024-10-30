package cn.xdzn.oj.service.problem.domain.training.service;

import cn.xdzn.oj.service.problem.domain.training.entity.po.Training;
import cn.xdzn.oj.service.problem.domain.training.entity.vo.TrainingCategoryVO;
import cn.xdzn.oj.service.problem.interfaces.dto.TrainingFrontDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author Shelly6
* @description 针对表【training】的数据库操作Service
* @createDate 2024-10-23 17:49:23
*/
public interface TrainingDomainService extends IService<Training> {

    List<TrainingFrontDTO> fill(List<TrainingFrontDTO> records, Map<Long, Integer> problemNumMap, Map<Integer, TrainingCategoryVO> categoryMap, Map<Integer, Integer> acNums);

    void deleteCategory(Long id);

    void addProblem(Long tid, Long pid,String displayId);

    void deleteProblem(Long id);
}
