package com.qmdx00.util;

/**
 * @author yuanweimin
 * @date 19/06/11 13:03
 * @description 参数验证
 */
@SuppressWarnings("ALL")
public class VerifyUtil {
    /**
     * 检验字符串非空且不为""
     *
     * @param strings 字符串
     * @return boolean
     */
    public static boolean checkString(String... strings) {
        for (String str : strings) {
            if (str == null) return false;
            else if (str.trim().equals("")) return false;
        }
        return true;
    }

    /**
     * 检验整数存在且大于 0
     *
     * @param ints 整数
     * @return boolean
     */
    public static boolean checkInteger(Integer... ints) {
        for (Integer integer : ints) {
            if (integer == null) return false;
            else if (integer <= 0) return false;
        }
        return true;
    }

    /**
     * 检验浮点数存在且大于 0
     *
     * @param doubles
     * @return
     */
    public static boolean checkDouble(Double... doubles) {
        for (Double dou : doubles) {
            if (dou == null) return false;
            else if (dou <= 0.0) return false;
        }
        return true;
    }
}