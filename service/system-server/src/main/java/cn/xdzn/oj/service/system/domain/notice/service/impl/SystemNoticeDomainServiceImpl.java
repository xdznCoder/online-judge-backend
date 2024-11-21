package cn.xdzn.oj.service.system.domain.notice.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.xdzn.oj.common.client.UserClient;
import cn.xdzn.oj.common.constants.CodeEnum;
import cn.xdzn.oj.common.exception.CustomException;
import cn.xdzn.oj.service.system.domain.notice.entity.po.SystemNotice;
import cn.xdzn.oj.service.system.domain.notice.entity.po.SystemNoticeUser;
import cn.xdzn.oj.service.system.domain.notice.service.SystemNoticeDomainService;
import cn.xdzn.oj.service.system.infrastructure.dao.SystemNoticeDao;
import cn.xdzn.oj.service.system.infrastructure.dao.SystemNoticeUserDao;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final SystemNoticeUserDao systemNoticeUserDao;
    private final UserClient userClient;

//    @Override
//    public void broadcastToUsers(List<Long> ids, Long id) {
//        SystemNotice systemNotice = getById(id);
//        if(systemNotice == null){
//            throw new CustomException(CodeEnum.DATA_NOT_EXIST);
//        }
//        String message = systemNotice.getContent();
//        WebSocketFrontServer.sendMessagesTo(ids, WebsocketResult.success(WebsocketEnum.SYSTEM_NOTICE).setMessage(message).setData(id));
//    }
//
//    @Override
//    public void broadcastToAll(Long id) {
//        SystemNotice systemNotice = getById(id);
//        if(systemNotice == null){
//            throw new CustomException(CodeEnum.DATA_NOT_EXIST);
//        }
//        String message = systemNotice.getContent();
//        WebSocketFrontServer.broadcastMessages(WebsocketResult.success(WebsocketEnum.SYSTEM_NOTICE).setMessage(message).setData(id));
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void push2Users(List<Long> ids, int type, Long id) {
        SystemNotice systemNotice = getById(id);
        if (systemNotice == null) {
            throw new CustomException(CodeEnum.DATA_NOT_EXIST);
        }

        switch (type) {
            case 1 -> {
                if (ids == null || ids.isEmpty()) {
                    throw new CustomException(CodeEnum.PARAMETER_ERROR);
                }
                systemNotice.setState(1);
                updateById(systemNotice);
                List<SystemNoticeUser> systemNoticeUsers = ids.stream()
                        .map(userId -> new SystemNoticeUser()
                                .setNoticeId(id)
                                .setUserId(userId)
                                .setIsRead(0))
                        .toList();
                systemNoticeUserDao.insert(systemNoticeUsers);
            }
            case 2 -> {
                List<Long> userIds = userClient.getAllId();
                List<SystemNoticeUser> systemNoticeUsers = userIds.stream()
                        .map(userId -> new SystemNoticeUser()
                                .setNoticeId(id)
                                .setUserId(userId)
                                .setIsRead(0))
                        .toList();
                systemNoticeUserDao.insert(systemNoticeUsers);
            }
            default -> throw new CustomException(CodeEnum.PARAMETER_ERROR);
        }
    }

    @Override
    public Integer getUnReadCount(Long userId) {
        return Math.toIntExact(systemNoticeUserDao.selectCount(new LambdaQueryWrapper<SystemNoticeUser>()
                .eq(SystemNoticeUser::getIsRead,0)
                .eq(SystemNoticeUser::getUserId, userId)
                .eq(SystemNoticeUser::getIsRead, 0)));
    }

    @Override
    public void readNotice(Long loginIdAsLong) {
        if (loginIdAsLong == null) {
            throw new CustomException(CodeEnum.LOGIN_EXPIRED);
        }
        LambdaUpdateWrapper<SystemNoticeUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(SystemNoticeUser::getIsRead, 1)
                .eq(SystemNoticeUser::getUserId, loginIdAsLong) // 匹配用户 ID
                .eq(SystemNoticeUser::getIsRead, 0)
                .eq(SystemNoticeUser::getIsDeleted, 0);
        // 执行更新操作
        systemNoticeUserDao.update(updateWrapper);
    }

    @Override
    public void deleteNotice(Long id) {
        if(id != -1L){
            systemNoticeUserDao.deleteById(id);
        }
        else{
            LambdaQueryWrapper<SystemNoticeUser> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SystemNoticeUser::getUserId, StpUtil.getLoginIdAsLong());
            systemNoticeUserDao.delete(queryWrapper);
        }
    }

}




