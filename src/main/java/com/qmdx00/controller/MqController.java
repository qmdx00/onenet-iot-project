package com.qmdx00.controller;

import com.qmdx00.onenet.mq.handler.MachineStatusHandler;
import com.qmdx00.onenet.mq.MqClient;
import com.qmdx00.onenet.mq.handler.ProductDataHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
@PropertySource("classpath:config/mqClient-config.properties")
@RequestMapping("/api/mq")
public class MqController extends BaseController {

    private final Environment env;
    private final MqClient productClient;
    private final MqClient machineClient;
    private final ProductDataHandle productDataHandle;
    private final MachineStatusHandler machineStatusHandler;

    @Autowired
    public MqController(MqClient machineClient, MqClient productClient, ProductDataHandle productDataHandle, MachineStatusHandler machineStatusHandler, Environment env) {
        this.machineClient = machineClient;
        this.productClient = productClient;
        this.productDataHandle = productDataHandle;
        this.machineStatusHandler = machineStatusHandler;
        this.env = env;
    }

    /**
     * 边缘网关机器状态数据订阅
     */
    @GetMapping("/sub/machine")
    public void subMachine() {
        machineClient.setTopic("test-topic");
        machineClient.setSub("demo");
        machineClient.setClientID(env.getProperty("machine.clientID"));
        machineClient.setMQID(env.getProperty("machine.MQID"));
        machineClient.setAccessKey(env.getProperty("machine.accessKey"));
        machineClient.setHandler(machineStatusHandler);
        machineClient.connect();
    }

    /**
     * 产品溯源系统数据订阅
     */
    @GetMapping("/sub/product")
    public void subProduct() {
        productClient.setTopic("test-topic");
        productClient.setSub("demo");
        productClient.setClientID(env.getProperty("product.clientID"));
        productClient.setMQID(env.getProperty("product.MQID"));
        productClient.setAccessKey(env.getProperty("product.accessKey"));
        productClient.setHandler(productDataHandle);
        productClient.connect();
    }
}
