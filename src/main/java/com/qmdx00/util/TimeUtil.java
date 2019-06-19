package com.qmdx00.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yuanweimin
 * @date 19/06/19 09:13
 * @description 时间格式化工具类
 */
@SuppressWarnings("unused")
public class TimeUtil {
    /**
     * 将13位数时间戳装换成字符串
     *
     * @param timestamp 时间戳
     * @return String
     */
    public static String toTime(String timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(Double.parseDouble(timestamp));
    }

    /**
     * 将时间戳转换成时间对象
     *
     * @param timestamp 时间戳
     * @return Date
     */
    public static Date toDate(String timestamp) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .parse(toTime(timestamp));
        } catch (ParseException e) {
            return null;
        }
    }
}
