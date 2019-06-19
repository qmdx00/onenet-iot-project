package com.qmdx00.controller;

import com.qmdx00.onenet.mq.handler.MachineStatusHandler;
import com.qmdx00.onenet.mq.MqClient;
import com.qmdx00.onenet.mq.handler.ProductDataHandle;
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
@SuppressWarnings("SpellCheckingInspection")
@Slf4j
@RestController
@RequestMapping("/api/mq")
public class MqController extends BaseController {

    private final MqClient productClient;
    private final MqClient machineClient;
    private ProductDataHandle productDataHandle;
    private final MachineStatusHandler machineStatusHandler;

    @Autowired
    public MqController(MqClient machineClient, MqClient productClient, ProductDataHandle productDataHandle, MachineStatusHandler machineStatusHandler) {
        this.machineClient = machineClient;
        this.productClient = productClient;
        this.productDataHandle = productDataHandle;
        this.machineStatusHandler = machineStatusHandler;
    }

    /**
     * 边缘网关机器状态数据订阅
     */
    @GetMapping("/sub/machine")
    public void subMachine() {
        machineClient.setTopic("test-topic");
        machineClient.setSub("demo");
        machineClient.setClientID("machine-client");
        machineClient.setMQID("A5F477CEA6CB427B40C2D138378167E5D");
        machineClient.setAccessKey("id+ExuQs5PLyTFlUucb2iKnkqPL4HiSTEXJgTM+YuZ8=");
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
        productClient.setClientID("product-client");
        productClient.setMQID("A7B0C794B9F569458C40EB09F812328EA");
        productClient.setAccessKey("FVJNx6D+q1prjnr7Drr4YpDog+S5cgQh37SHDCxZ/+c=");
        productClient.setHandler(productDataHandle);
        productClient.connect();
    }
}
