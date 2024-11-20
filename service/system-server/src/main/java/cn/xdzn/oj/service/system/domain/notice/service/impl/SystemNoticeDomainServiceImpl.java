package cn.xdzn.oj.service.system.domain.notice.service.impl;

import cn.xdzn.oj.common.constants.CodeEnum;
import cn.xdzn.oj.common.exception.CustomException;
import cn.xdzn.oj.service.system.domain.notice.entity.po.SystemNotice;
import cn.xdzn.oj.service.system.domain.notice.entity.vo.WebsocketResult;
import cn.xdzn.oj.service.system.domain.notice.service.SystemNoticeDomainService;
import cn.xdzn.oj.service.system.infrastructure.dao.SystemNoticeDao;
import cn.xdzn.oj.service.system.infrastructure.enums.WebsocketEnum;
import cn.xdzn.oj.service.system.infrastructure.server.WebSocketFrontServer;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Shelly6
* @description 针对表【system_notice】的数据库操作Service实现
* @createDate 2024-11-20 17:44:25
*/
@Service
@RequiredArgsConstructor
public class SystemNoticeDomainServiceImpl extends ServiceImpl<SystemNoticeDao, SystemNotice>
    implements SystemNoticeDomainService {

    @Override
    public void broadcastToUsers(List<Long> ids, Long id) {
        SystemNotice systemNotice = getById(id);
        if(systemNotice == null){
            throw new CustomException(CodeEnum.DATA_NOT_EXIST);
        }
        String message = systemNotice.getContent();
        WebSocketFrontServer.sendMessagesTo(ids, WebsocketResult.success(WebsocketEnum.SYSTEM_NOTICE).setMessage(message).setData(id));
    }

    @Override
    public void broadcastToAll(Long id) {
        SystemNotice systemNotice = getById(id);
        if(systemNotice == null){
            throw new CustomException(CodeEnum.DATA_NOT_EXIST);
        }
        String message = systemNotice.getContent();
        WebSocketFrontServer.broadcastMessages(WebsocketResult.success(WebsocketEnum.SYSTEM_NOTICE).setMessage(message).setData(id));
    }
}




