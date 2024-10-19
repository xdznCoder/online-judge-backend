package cn.xdzn.oj.common.util;

import cn.xdzn.oj.common.exception.CustomException;

import java.util.Random;

/**
 * 随机生成验证码工具类
 *
 * @author HeXin
 * @date 2024/03/08
 */
public class ValidateCodeUtils {

    private ValidateCodeUtils() {
        // do nothing
    }

    static final Random random = new Random();

    /**
     * 随机生成验证码
     *
     * @param length 声场验证码长度为4位或者6位
     */
    public static Integer generateValidateCodeUtils(int length) {
        Integer code = null;
        if (length == 4) {
            code = random.nextInt(9999);
            if (code < 1000) {
                code = code + 1000;//保证随机数为4位数字
            }
        } else if (length == 6) {
            code = random.nextInt(999999);
            if (code < 100000) {
                code = code + 100000;//保证随机数为6位数字
            }
        } else {
            throw new CustomException("只能生成四位或者六位数字验证码");
        }
        return code;
    }
}
