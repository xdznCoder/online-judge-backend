package cn.xdzn.oj.common.constants;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * redis常量
 * @author HeXin
 */

@Getter
public enum RedisConstants {

    /**
     * 默认
     */
    DEFAULT("",10L,TimeUnit.SECONDS),
    /**
     * 用户常量
     */
    USER("User:", 1800L, TimeUnit.SECONDS),
    /**
     * 用户角色常量
     */
    USER_ROLE("User:role:", 1800L, TimeUnit.SECONDS),
    /**
     * 用户权限常量
     */
    USER_PERMISSION("User:permission:", 1800L, TimeUnit.SECONDS),
    /**
     * 课程浏览常量
     */
    COURSE_VIEW_COUNT("Course:viewCount:", 3600L,TimeUnit.SECONDS),

    /**
     * 课程点赞常量
     */
    COURSE_LIKE_COUNT("Course:likeCount:", 3600L,TimeUnit.SECONDS),

    /**
     * 用户点赞常量
     */

    USER_COURSE_LIKE("User:courseLike:", 3600L,TimeUnit.SECONDS),
    /**
      * 活动即将开始
      */
    ACTIVITY_UPCOMING("Activity:upcoming", 86400L, TimeUnit.SECONDS),
    /**
      * 活动即将结束
      */
    ACTIVITY_ENDING("Activity:ending", 86400L, TimeUnit.SECONDS),
    /**
     * 邮箱验证码常量
     */
    EMAIL_CODE("Email:code:", 300L, TimeUnit.SECONDS),
    /**
     * 手机验证码常量
     */
    PHONE_CODE("Phone:code:", 300L, TimeUnit.SECONDS),
    /**
     * 验证码常量
     */

    VERIFICATION_CODE("Verification:code:", 300L, TimeUnit.SECONDS),

    USER_TOKEN("User:token:", 3L,TimeUnit.DAYS ),
    USER_INFO("User:info:",3L ,TimeUnit.DAYS );
    /**
     * 键
     */
    private final String key;
    /**
     * 过期时间
     */
    private final Long ttl;

    /**
     * 时间单位
     */
    private final TimeUnit timeUnit;

    RedisConstants(String key, Long ttl, TimeUnit timeUnit) {
        this.key = key;
        this.ttl = ttl;
        this.timeUnit = timeUnit;
    }

}
