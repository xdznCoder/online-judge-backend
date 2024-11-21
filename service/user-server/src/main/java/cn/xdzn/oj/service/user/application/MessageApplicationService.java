package cn.xdzn.oj.service.user.application;

import cn.dev33.satoken.stp.StpUtil;
import cn.xdzn.oj.common.client.SystemClient;
import cn.xdzn.oj.common.constants.CodeEnum;
import cn.xdzn.oj.common.exception.CustomException;
import cn.xdzn.oj.service.user.domain.message.entity.po.UserMsg;
import cn.xdzn.oj.service.user.domain.message.service.UserMsgDomainService;
import cn.xdzn.oj.service.user.infrastructure.enums.MessageType;
import cn.xdzn.oj.service.user.interfaces.dto.MessageUnReadDTO;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageApplicationService {
    private final UserMsgDomainService userMsgDomainService;
    private final SystemClient systemClient;
    public MessageUnReadDTO getUnReadMessage() {
        Long userId = StpUtil.getLoginIdAsLong();
        //先查询其他几个状态的未读消息
        List<UserMsg> unReadList = userMsgDomainService.lambdaQuery()
                .select(UserMsg::getId, UserMsg::getAction)
                .eq(UserMsg::getRecipientId, userId)
                .eq(UserMsg::getState, 0)
                .eq(UserMsg::getIsDeleted, 0)
                .list();

        Map<Integer,Integer> map = unReadList.stream()
                .collect(Collectors.groupingBy(UserMsg::getAction,
                        Collectors.collectingAndThen(Collectors.counting(),Long::intValue)));
            MessageUnReadDTO dto = new MessageUnReadDTO()
                    .setCommentUnRead(map.getOrDefault(MessageType.COMMENT.getValue(),0))
                    .setLikeUnRead(map.getOrDefault(MessageType.DB_LIKE1.getValue(),0)+map.getOrDefault(MessageType.DB_LIKE2.getValue(),0))
                    .setOtherUnRead(map.getOrDefault(MessageType.OTHER.getValue(),0))
                    .setReplyUnRead(map.getOrDefault(MessageType.REPLY.getValue(),0));

            //查询系统通知未读消息
            dto.setNoticeUnRead(systemClient.getNoticeUnRead(userId));
            return dto;
    }

    public void readMessage(int type) {
        if (!MessageType.isValidType(type)) {
            throw new CustomException(CodeEnum.PARAMETER_ERROR);
        }
        //1为评论，2为回复，3为点赞，4为其他，5为系统
        if(type == MessageType.SYSTEM.getValue()){
            systemClient.readNotice(StpUtil.getLoginIdAsLong());
            return;
        }
        // 构造查询条件
        LambdaUpdateWrapper<UserMsg> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(UserMsg::getState, 1)
                .eq(UserMsg::getState, 0)
                .eq(UserMsg::getRecipientId, StpUtil.getLoginIdAsLong())
                .eq(UserMsg::getIsDeleted, 0);

        // 类型筛选
        if (type != MessageType.LIKE.getValue()) {
            updateWrapper.eq(UserMsg::getAction, type);
        } else {
            updateWrapper.in(UserMsg::getAction, Arrays.asList(MessageType.DB_LIKE1.getValue(), MessageType.DB_LIKE2.getValue())); // 点赞和某些相关类型
        }

        boolean isUpdated = userMsgDomainService.update(updateWrapper);

        if (!isUpdated) {
            throw new CustomException(CodeEnum.FAIL);
        }

    }

    public void delete(Long id, int type) {
        if (id == null || !MessageType.isValidType(type)) {
            throw new CustomException(CodeEnum.PARAMETER_ERROR);
        }
        //1为评论，2为回复，3为点赞，4为其他，5为系统
        if(type == MessageType.SYSTEM.getValue()){
            systemClient.deleteNotice(id);
            return;
        }
        if(id != -1L) userMsgDomainService.removeById(new UserMsg().setId(id));
        else{
            userMsgDomainService.lambdaUpdate()
                    .eq(UserMsg::getRecipientId, StpUtil.getLoginIdAsLong())
                    .eq(UserMsg::getIsDeleted, 0)
                    .remove();
        }
    }
}
