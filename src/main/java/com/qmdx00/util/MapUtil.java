package com.qmdx00.util;

import java.util.HashMap;

/**
 * @author yuanweimin
 * @date 19/06/12 10:17
 * @description map 工具类，格式化输出
 */
@SuppressWarnings("ALL")
public class MapUtil {
    /**
     * 通过 strings 构造 Map
     *
     * @param strings 构造参数
     * @return HashMap
     */
    public static HashMap<String, String> create(String... strings) {
        HashMap<String, String> map = new HashMap<>();
        if ((strings.length & 1) == 1) return map;
        else {
            for (int i = 0; i < strings.length; i += 2) {
                map.put(strings[i], strings[i + 1]);
            }
        }
        return map;
    }
}
