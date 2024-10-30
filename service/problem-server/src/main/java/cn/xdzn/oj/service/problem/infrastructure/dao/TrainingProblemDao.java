package cn.xdzn.oj.service.problem.infrastructure.dao;

import cn.xdzn.oj.service.problem.domain.training.entity.po.TrainingProblem;
import cn.xdzn.oj.service.problem.domain.training.entity.vo.TrainingNumVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author Shelly6
* @description 针对表【training_problem】的数据库操作Mapper
* @createDate 2024-10-24 13:44:05
* @Entity .cn.xdzn.oj.service.problem.domain.Training.entity.po.TrainingProblem
*/
@Mapper
public interface TrainingProblemDao extends BaseMapper<TrainingProblem> {

    List<TrainingNumVO> getProblemNum(List<Long> list);

    List<Integer> getProblemIds(Long id);

    void addProblem(Long tid, Long pid,String displayId);
}




