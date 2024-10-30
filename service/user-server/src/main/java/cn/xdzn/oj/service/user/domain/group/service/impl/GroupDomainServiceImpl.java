package cn.xdzn.oj.service.user.domain.group.service.impl;

import cn.xdzn.oj.service.user.domain.group.entity.po.Group;
import cn.xdzn.oj.service.user.domain.group.service.GroupDomainService;
import cn.xdzn.oj.service.user.infrastructure.dao.GroupDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author Shelly6
* @description 针对表【group】的数据库操作Service实现
* @createDate 2024-10-28 13:34:48
*/
@Service
public class GroupDomainServiceImpl extends ServiceImpl<GroupDao, Group>
    implements GroupDomainService {

}




