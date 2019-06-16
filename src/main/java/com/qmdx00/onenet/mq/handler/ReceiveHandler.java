package com.qmdx00.onenet.mq.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yuanweimin
 * @date 19/06/13 10:54
 * @description 消息处理类
 */
@Slf4j
@Component
public class ReceiveHandler implements MessageHandler {

    private final SimpMessagingTemplate template;

    @Autowired
    public ReceiveHandler(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Override
    public synchronized void handle(long msgId, String msgBody) {
        log.info("msgId: {}, msgBody: {}", msgId, msgBody);
        JSONObject msg = JSON.parseObject(msgBody);
        JSONObject prop = msg.getJSONObject("appProperty");
        log.info("body: {}, timestamp: {}", msg.getString("body"), prop.getString("dataTimestamp"));
        template.convertAndSend("/topic/msg", msg.getString("body"));
    }
}
