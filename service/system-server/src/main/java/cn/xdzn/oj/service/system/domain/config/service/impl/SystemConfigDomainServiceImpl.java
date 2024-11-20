package cn.xdzn.oj.service.system.domain.config.service.impl;

import cn.xdzn.oj.service.system.domain.config.entity.po.SystemConfig;
import cn.xdzn.oj.service.system.domain.config.service.SystemConfigDomainService;


import cn.xdzn.oj.service.system.infrastructure.dao.SystemConfigDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author Shelly6
* @description 针对表【system_config(系统配置表)】的数据库操作Service实现
* @createDate 2024-11-16 14:31:47
*/
@Service
public class SystemConfigDomainServiceImpl extends ServiceImpl<SystemConfigDao, SystemConfig>
    implements SystemConfigDomainService {

}




