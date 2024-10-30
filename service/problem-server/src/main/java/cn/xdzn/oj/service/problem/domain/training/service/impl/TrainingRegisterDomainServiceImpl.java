package cn.xdzn.oj.service.problem.domain.training.service.impl;

import cn.xdzn.oj.service.problem.domain.training.entity.po.TrainingRegister;
import cn.xdzn.oj.service.problem.domain.training.service.TrainingRegisterDomainService;
import cn.xdzn.oj.service.problem.infrastructure.dao.TrainingRegisterDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TrainingRegisterDomainServiceImpl extends ServiceImpl<TrainingRegisterDao, TrainingRegister>  implements TrainingRegisterDomainService {
}
