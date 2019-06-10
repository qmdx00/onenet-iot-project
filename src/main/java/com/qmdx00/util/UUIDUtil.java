package com.qmdx00.util;

import java.util.UUID;

/**
 * @author yuanweimin
 * @date 19/06/10 10:58
 * @description UUID 工具类
 */
public class UUIDUtil {
    public static String getUUID() {
        return UUID.randomUUID()
                .toString()
                .replace("-", "");
    }
}
