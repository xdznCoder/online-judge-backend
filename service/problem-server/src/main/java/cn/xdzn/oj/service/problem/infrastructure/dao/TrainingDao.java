package cn.xdzn.oj.service.problem.infrastructure.dao;

import cn.xdzn.oj.service.problem.domain.training.entity.po.Training;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Shelly6
* @description 针对表【training】的数据库操作Mapper
* @createDate 2024-10-23 17:49:23
* @Entity .domain.cn.xdzn.oj.service.problem.domain.cn.xdzn.oj.service.problem.domain.Training.entity.po.Training.entity.po.cn.xdzn.oj.service.problem.domain.Training.entity.po.Training
*/
@Mapper
public interface TrainingDao extends BaseMapper<Training> {

}




