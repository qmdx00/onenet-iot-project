package com.qmdx00.onenet.mq.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yuanweimin
 * @date 19/06/19 11:44
 * @description 生产过程数据处理类
 */
@Slf4j
@Component
public class ProductDataHandle implements MessageHandler {

    @Override
    public void handle(long msgId, String msgBody) {
        log.info("msgId: {}, msgBody: {}", msgId, msgBody);
    }
}
