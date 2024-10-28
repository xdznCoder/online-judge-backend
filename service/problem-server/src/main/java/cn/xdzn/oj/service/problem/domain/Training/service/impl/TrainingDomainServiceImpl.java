package cn.xdzn.oj.service.problem.domain.Training.service.impl;

import cn.xdzn.oj.common.exception.CustomException;
import cn.xdzn.oj.service.problem.domain.Training.entity.po.Training;
import cn.xdzn.oj.service.problem.domain.Training.entity.vo.TrainingCategoryVO;
import cn.xdzn.oj.service.problem.domain.Training.repository.TrainingProblemRepository;
import cn.xdzn.oj.service.problem.domain.Training.service.TrainingDomainService;
import cn.xdzn.oj.service.problem.infrastructure.dao.TrainingDao;
import cn.xdzn.oj.service.problem.interfaces.dto.TrainingFrontDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Shelly6
* @description 针对表【training】的数据库操作Service实现
* @createDate 2024-10-23 17:49:23
*/
@Service
@RequiredArgsConstructor
public class TrainingDomainServiceImpl extends ServiceImpl<TrainingDao, Training>
    implements TrainingDomainService {
    private final TrainingProblemRepository trainingProblemRepository;


    public List<TrainingFrontDTO> fill(List<TrainingFrontDTO> records,
                                       Map<Long, Integer> problemNumMap,
                                       Map<Integer, TrainingCategoryVO> categoryMap,
                                       Map<Integer, Integer> acNums) {
        for (TrainingFrontDTO trainingFrontDTO : records) {
            TrainingFrontDTO.fill(trainingFrontDTO,
                    problemNumMap.get(trainingFrontDTO.getId()),
                    acNums.get(trainingFrontDTO.getId().intValue()),
                    categoryMap.get(trainingFrontDTO.getCid()));
        }
        return records;
    }

    @Override
    public void deleteCategory(Long id) {
        Long count = lambdaQuery().select(Training::getCid).eq(Training::getCid, id).eq(Training::getIsDeleted,0).count();
        if (count > 0) {
            throw new CustomException("该分类下有训练，无法删除");
        }
        removeById(new Training().setId(id));
    }

    @Override
    public void addProblem(Long tid, Long pid,String displayId) {
        trainingProblemRepository.addProblem(tid, pid, displayId);
    }

    @Override
    public void deleteProblem(Long id) {
        trainingProblemRepository.deleteProblem(id);
    }
}




