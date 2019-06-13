package com.qmdx00.onenet.mq.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

/**
 * @author yuanweimin
 * @date 19/06/13 10:54
 * @description 消息处理类
 */
@Slf4j
public class ReceiveHandler implements MessageHandler {

    @Override
    public synchronized void handle(long msgId, String msgBody) {
        log.info("msgId: {}, msgBody: {}", msgId, msgBody);

        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject msg = JSON.parseObject(msgBody);
        JSONObject prop = msg.getJSONObject("appProperty");
        System.out.println(msg.getString("body"));
        System.out.println(format.format(prop.getString("dataTimestamp")));
        log.info("body: {}, time: {}", msg.getString("body"), format.format(prop.getString("dataTimestamp")));
    }
}
