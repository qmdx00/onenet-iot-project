package com.qmdx00.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qmdx00.util.model.Message;

/**
 * @author yuanweimin
 * @date 19/06/20 19:19
 * @description 工具类，解析 onenet 发来的消息
 */
public class MessageUtil {
    /**
     * 解析消息为Message实体
     *
     * @param id      消息 ID
     * @param content 消息内容
     * @return Message
     */
    public static Message analysis(Long id, String content) {
        JSONObject con = JSON.parseObject(content);
        JSONObject sys = con.getJSONObject("sysProperty");
        JSONObject app = con.getJSONObject("appProperty");
        return Message.builder()
                .msgId(id)
                .msgType(sys.getString("messageType"))
                .productId(sys.getString("productId"))
                .deviceId(app.getString("deviceId"))
                .timestamp(app.getString("dataTimestamp"))
                .datastream(app.getString("datastream"))
                .body(con.getString("body"))
                .build();
    }
}
