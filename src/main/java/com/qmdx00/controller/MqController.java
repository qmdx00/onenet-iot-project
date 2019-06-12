package com.qmdx00.controller;

import com.qmdx00.onenet.mq.MqClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mq")
public class MqController extends BaseController {

    private final MqClient client;

    @Autowired
    public MqController(MqClient client) {
        this.client = client;
    }

    @GetMapping("/sub")
    public void subscribe() {
        client.setTopic("test-topic");
        client.setSub("demo");
        client.connect();
    }
}
