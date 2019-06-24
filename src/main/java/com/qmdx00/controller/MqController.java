package com.qmdx00.controller;

import com.qmdx00.onenet.mq.handler.MachineStatusHandler;
import com.qmdx00.onenet.mq.MqClient;
import com.qmdx00.onenet.mq.handler.MessageHandler;
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
@SuppressWarnings("SameParameterValue")
@Slf4j
@RestController
@PropertySource("classpath:config/mqClient-config.properties")
@RequestMapping("/api/sub")
public class MqController extends BaseController {

    private final Environment env;
    private final MqClient productClient1;
    private final MqClient productClient2;
    private final MqClient productClient3;
    private final MqClient productClient4;
    private final MqClient machineClient;
    private final ProductDataHandle productDataHandle;
    private final MachineStatusHandler machineStatusHandler;

    @Autowired
    public MqController(MqClient machineClient, MqClient productClient1, MqClient productClient2, MqClient productClient3, MqClient productClient4, ProductDataHandle productDataHandle, MachineStatusHandler machineStatusHandler, Environment env) {
        this.machineClient = machineClient;
        this.productClient1 = productClient1;
        this.productClient2 = productClient2;
        this.productClient3 = productClient3;
        this.productClient4 = productClient4;
        this.productDataHandle = productDataHandle;
        this.machineStatusHandler = machineStatusHandler;
        this.env = env;
    }

    /**
     * 边缘网关机器状态数据订阅
     */
    @GetMapping("/machine")
    public void subMachine() {
        subscribe(machineClient,
                "test-topic",
                "demo",
                env.getProperty("machine.clientId"),
                env.getProperty("machine.MQID"),
                env.getProperty("machine.accessKey"),
                machineStatusHandler);
    }

    /**
     * 产品溯源系统数据订阅-第一道工序
     */
    @GetMapping("/product/1")
    public void subProduct1() {
        subscribe(productClient1,
                "test-topic",
                "demo",
                env.getProperty("product1.clientId"),
                env.getProperty("product1.MQID"),
                env.getProperty("product1.accessKey"),
                productDataHandle);
    }

    /**
     * 产品溯源系统数据订阅-第二道工序
     */
    @GetMapping("/product/2")
    public void subProduct2() {
        subscribe(productClient2,
                "test-topic",
                "demo",
                env.getProperty("product2.clientId"),
                env.getProperty("product2.MQID"),
                env.getProperty("product2.accessKey"),
                productDataHandle);
    }

    /**
     * 产品溯源系统数据订阅-第三道工序
     */
    @GetMapping("/product/3")
    public void subProduct3() {
        subscribe(productClient3,
                "test-topic",
                "demo",
                env.getProperty("product3.clientId"),
                env.getProperty("product3.MQID"),
                env.getProperty("product3.accessKey"),
                productDataHandle);
    }

    /**
     * 产品溯源系统数据订阅-第四道工序
     */
    @GetMapping("/product/4")
    public void subProduct4() {
        subscribe(productClient4,
                "test-topic",
                "demo",
                env.getProperty("product4.clientId"),
                env.getProperty("product4.MQID"),
                env.getProperty("product4.accessKey"),
                productDataHandle);
    }

    /**
     * 订阅方法封装
     *
     * @param client    客户端
     * @param topic     topic
     * @param sub       sub
     * @param clientId  客户端名称
     * @param MQID      消息队列 ID
     * @param accessKey 密钥
     * @param handler   消息处理
     */
    private void subscribe(MqClient client,
                           String topic,
                           String sub,
                           String clientId,
                           String MQID,
                           String accessKey,
                           MessageHandler handler) {

        client.setTopic(topic);
        client.setSub(sub);
        client.setClientID(clientId);
        client.setMQID(MQID);
        client.setAccessKey(accessKey);
        client.setHandler(handler);
        client.connect();
    }
}
