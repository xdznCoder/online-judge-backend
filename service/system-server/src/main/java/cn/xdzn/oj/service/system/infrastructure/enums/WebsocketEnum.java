//package cn.xdzn.oj.service.system.infrastructure.enums;
//
//import lombok.Getter;
//
///**
// * @author m
// * @date 2023/11/4
// * @Description
// */
//@Getter
//public enum WebsocketEnum {
//    /**
//     * ping
//     */
//    PING(1, "survival"),
//    SYSTEM_NOTICE(2, "系统通知"),
//    ADMITTED(2,"已录取"),
//    COURSE_BEGIN(3, "课程开始"),
//    SIGN_BEGIN(4, "开始签到"),
//    DENIED(5,"未录取"),
//    ASSESSMENT_OVER(6,"考评已完成"),
//    NOT_LOGIN(202, "登陆信息校验失败"),
//    NO_PERMISSION(403, "权限异常");
//    public static final String PING_MESSAGE = "ping";
//    private final Integer type;
//    private final String message;
//
//    WebsocketEnum(Integer type, String massage) {
//        this.type = type;
//        this.message = massage;
//    }
//
//}
