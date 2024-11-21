package cn.xdzn.oj.service.user.infrastructure.enums;

import lombok.Getter;

/**
 * 消息类型
 * @author shelly
 */
@Getter
public enum MessageType {

    COMMENT(1),    // 评论
    REPLY(2),      // 回复
    LIKE(3),       // 点赞
    OTHER(4),      // 其他
    SYSTEM(5),    // 系统消息

    //********************数据库特殊常量************************************
    DB_LIKE1(3),
    DB_LIKE2(5);

    private final int value;

    MessageType(int value) {
        this.value = value;
    }

    public static boolean isValidType(int value) {
        for (MessageType type : MessageType.values()) {
            if (type.getValue() == value) {
                return true;
            }
        }
        return false;
    }
}
