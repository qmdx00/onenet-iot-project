package com.qmdx00.handler;

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
     */
    void handle();
}
