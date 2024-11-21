package cn.xdzn.oj.service.user.domain.message.service.impl;

import cn.xdzn.oj.service.user.domain.message.entity.po.UserMsg;
import cn.xdzn.oj.service.user.domain.message.service.UserMsgDomainService;
import cn.xdzn.oj.service.user.infrastructure.dao.UserMsgDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UseMsgDomainServiceImpl  extends ServiceImpl<UserMsgDao, UserMsg>
    implements UserMsgDomainService {
}
