package cn.xdzn.oj.service.problem.domain.Training.service.impl;


import cn.xdzn.oj.service.problem.domain.Training.entity.po.Training;
import cn.xdzn.oj.service.problem.domain.Training.service.TrainingDomainService;
import cn.xdzn.oj.service.problem.infrastructure.dao.TrainingDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author Shelly6
* @description 针对表【training】的数据库操作Service实现
* @createDate 2024-10-23 17:49:23
*/
@Service
public class TrainingDomainServiceImpl extends ServiceImpl<TrainingDao, Training>
    implements TrainingDomainService {

}




