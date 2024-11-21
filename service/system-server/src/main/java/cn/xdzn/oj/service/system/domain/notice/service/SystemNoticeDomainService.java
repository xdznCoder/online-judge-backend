package cn.xdzn.oj.service.system.domain.notice.service;

import cn.xdzn.oj.service.system.domain.notice.entity.po.SystemNotice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Shelly6
* @description 针对表【system_notice】的数据库操作Service
* @createDate 2024-11-20 17:44:25
*/
public interface SystemNoticeDomainService extends IService<SystemNotice> {

//    void broadcastToUsers(List<Long> ids, Long id);
//
//    void broadcastToAll(Long id);

    void push2Users(List<Long> ids, int type,Long id);

    Integer getUnReadCount(Long userId);

    void readNotice(Long loginIdAsLong);

    void deleteNotice(Long id);
}
