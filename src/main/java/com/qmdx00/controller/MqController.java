package com.qmdx00.controller;

import com.qmdx00.onenet.mq.handler.ReceiveHandler;
import com.qmdx00.onenet.mq.MqClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanweimin
 * @date 19/06/17 08:43
 * @description 消息推送订阅 Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/mq")
public class MqController extends BaseController {

    private final ReceiveHandler handler;
    private final MqClient client;

    @Autowired
    public MqController(MqClient client, ReceiveHandler handler) {
        this.client = client;
        this.handler = handler;
    }

    @GetMapping("/sub")
    public void subscribe() {
        client.setTopic("test-topic");
        client.setSub("demo");
        client.setHandler(handler);
        client.connect();
    }
}
