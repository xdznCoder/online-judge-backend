package cn.xdzn.oj.service.problem.infrastructure.dao;

import cn.xdzn.oj.service.problem.domain.Training.entity.po.TrainingRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Shelly6
* @description 针对表【training_record】的数据库操作Mapper
* @createDate 2024-10-24 13:44:05
* @Entity .cn.xdzn.oj.service.problem.domain.Training.entity.po.TrainingRecord
*/
@Mapper
public interface TrainingRecordDao extends BaseMapper<TrainingRecord> {

}




