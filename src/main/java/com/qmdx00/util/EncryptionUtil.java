package com.qmdx00.util;

import org.springframework.util.DigestUtils;

/**
 * @author yuanweimin
 * @date 19/06/11 09:17
 * @description 加密工具类
 */
public class EncryptionUtil {
    /**
     * 对字符串进行md5加密
     *
     * @param source 原字符串
     * @return 加密后字符串
     */
    public static String encrypt(String source) {
        return DigestUtils.md5DigestAsHex(source.getBytes());
    }
}
