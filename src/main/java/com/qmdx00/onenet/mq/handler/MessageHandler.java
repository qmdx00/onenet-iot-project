package com.qmdx00.onenet.mq.handler;

import org.springframework.stereotype.Component;

/**
 * @author yuanweimin
 * @date 19/06/12 18:21
 * @description client 接收到消息的处理接口
 */
@Component
public interface MessageHandler {
    /**
     * 处理消息方法
     *
     * @param msgId   消息 ID
     * @param msgBody 消息内容
     */
    void handle(long msgId, String msgBody);
}
