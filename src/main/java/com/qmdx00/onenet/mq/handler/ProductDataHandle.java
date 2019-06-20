package com.qmdx00.onenet.mq.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qmdx00.entity.ProductData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

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
        JSONObject msg = JSON.parseObject(msgBody);
        JSONObject prop = msg.getJSONObject("appProperty");
        String timestamp = prop.getString("dataTimestamp");
        String deviceId = prop.getString("deviceId");
        String body = msg.getString("body");
        log.info("body: {}, deviceId: {}, timestamp: {}", body, deviceId, timestamp);
        ProductData data = translate(deviceId, timestamp, body);
    }

    /**
     * 将数据解析成 ProductData 对象
     *
     * @param deviceId  设备 ID
     * @param timestamp 时间戳
     * @param body      数据体
     * @return ProductData
     */
    private ProductData translate(String deviceId, String timestamp, String body) {
        Map<String, String> map = Arrays.stream(body.split("-"))
                .map(et -> et.split("@"))
                .filter(et -> et.length == 2 && !et[1].equals("#"))
                .collect(Collectors.toMap(et -> et[0], et -> et[1]));
        System.out.println(map);
        return ProductData.builder().build();
    }
}
