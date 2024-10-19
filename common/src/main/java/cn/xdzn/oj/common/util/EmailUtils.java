package cn.xdzn.oj.common.util;


import cn.xdzn.oj.common.constants.CodeEnum;
import cn.xdzn.oj.common.exception.CustomException;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;


/**
 * 邮箱工具类
 *
 * @author HeXin
 * @date 2024/03/08
 */
@Component
public class EmailUtils {

    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sendMailer;

    /**
     * 判断邮箱是否合法
     */
    public static void isValidEmail(String email) {
        if (StringUtils.isBlank(email)) {
            throw new CustomException(CodeEnum.EMAIL_EMPTY);
        }
        if (!Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email)) {
            throw new CustomException(CodeEnum.EMAIL_FORMAT_ERROR);
        }
    }

    @Async
    public void sendMailMessage(String email, String code) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            // 发件人邮箱
            message.setFrom(sendMailer);
            // 收件人邮箱
            message.setTo(email);
            // 邮件标题
            message.setSubject("欢迎使用虚动智能 OJ 系统！");
            message.setText("这是您的注册验证码：\n" + "<font color='blue'>" + code + "</font>" + "\n验证码五分钟内有效。若非本人操作，请忽略此邮件！");
            mailSender.send(message);
        } catch (MailException e) {
            throw new CustomException(CodeEnum.EMAIL_SEND_ERROR);
        }
    }
}