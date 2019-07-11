package com.qmdx00.onenet.mq;

import com.google.protobuf.InvalidProtocolBufferException;
import com.qmdx00.onenet.mq.handler.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yuanweimin
 * @date 19/06/12 18:07
 * @description 消息接收
 */
@SuppressWarnings("unused")
@Slf4j
public class PushCallback implements MqttCallback {
    private ExecutorService service = Executors.newCachedThreadPool();
    private IMqttAsyncClient Client;
    private MessageHandler handler;
    private MqClient mqClient;
    private int reConnTimes = 0;

    PushCallback(MqClient mqClient, MessageHandler handler) {
        this.mqClient = mqClient;
        this.handler = handler;
    }

    @Override
    public void connectionLost(Throwable cause) {
        log.info("connect is lost,and try to reconnect");
        while (!mqClient.reConnect()) {
            try {
                if (reConnTimes++ > 20) {//前20次每秒重连一次
                    Thread.sleep(1000);
                } else {//超过20次后每10s重连一次
                    Thread.sleep(10000);
                }
            } catch (InterruptedException e) {
                log.error("{}", e.getMessage());
            }
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws InvalidProtocolBufferException {
        byte[] payload = message.getPayload();
        OnenetMq.Msg obj = OnenetMq.Msg.parseFrom(payload);
        service.execute(() -> handler.handle(obj.getMsgid(), new String(obj.getData().toByteArray())));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        Client = token.getClient();
    }

    public IMqttAsyncClient getClient() {
        return Client;
    }
}
