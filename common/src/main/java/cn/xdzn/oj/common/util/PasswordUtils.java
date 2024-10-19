package cn.xdzn.oj.common.util;

import cn.hutool.crypto.digest.MD5;

/**
 * 密码工具类
 *
 * @author HeXin
 * @date 2024/03/08
 */
public class PasswordUtils {
    /**
     * 加密盐(需要自行修改，否则密码将会被破解)
     */
    static final String SLAT = "swpuxdzn";

    private PasswordUtils() {
    }

    /**
     * 加密
     */
    public static String encrypt(String password) {
        MD5 md5 = new MD5(SLAT.getBytes());
        return md5.digestHex16(md5.digestHex(password));
    }

    /**
     * 密码配对
     */
    public static boolean match(String password, String encryptedPassword) {
        return encryptedPassword.equals(encrypt(password));
    }
}
