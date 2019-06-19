package com.qmdx00.onenet.mq;

import com.qmdx00.onenet.mq.handler.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Objects;

/**
 * @author yuanweimin
 * @date 19/06/12 17:10
 * @description MQ 客户端代码
 */
@Slf4j
@Component
@Scope(value = "prototype")
public class MqClient {
    private MqttConnectOptions options = new MqttConnectOptions();
    private MqttClient client;
    private String subTopic;

    private String topic;
    private String sub;
    private String clientID;
    private String MQID;
    private String accessKey;
    private MessageHandler handler;

    public synchronized boolean connect() {
        String clientID = this.clientID;
        String userName = this.MQID;
        String serverURI = "ssl://183.230.40.96:8883";
        try {
            if (null == client) {
                if (clientID != null) {
                    client = new MqttClient(serverURI, clientID, new MemoryPersistence());
                }
            }
            resetOptions();
            try {
                if (!client.isConnected()) {
                    client.connect(options);
                }
            } catch (MqttException e) {
                e.printStackTrace();
            }
            subTopic = String.format("$sys/pb/consume/%s/%s/%s", userName, this.topic, this.sub);
            client.setCallback(new PushCallback(this, this.handler));

            try {
                //订阅 topic $sys/pb/consume/$MQ_ID/$TOPIC/$SUB ，QoS必须大于0，否则订阅失败
                client.subscribe(subTopic, 2);
                log.info("sub success");
                return true;
            } catch (MqttException e) {
                log.error("sub failed");
                e.printStackTrace();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void resetOptions() {
        String userName = this.MQID;
        String accessKey = this.accessKey;

        String version = "2018-10-31";
        String resourceName = "mqs/" + userName;
        String expirationTime = System.currentTimeMillis() / 1000 + 100 * 24 * 60 * 60 + "";
        String signatureMethod = "md5";
        String password = null;
        try {
            //生成token
            password = Token.assembleToken(version, resourceName, expirationTime, signatureMethod, accessKey);
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        options.setCleanSession(true);
        options.setUserName(userName);
        options.setPassword(Objects.requireNonNull(password).toCharArray());
        options.setConnectionTimeout(20);
        options.setKeepAliveInterval(30);
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
        InputStream caCrtFile;
        try {
            caCrtFile = this.getClass().getResource("/config/certificate.pem").openStream();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            options.setSocketFactory(SslUtil.getSocketFactory(caCrtFile));
        } catch (NoSuchAlgorithmException | KeyStoreException | CertificateException
                | IOException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    boolean reConnect() {
        if (null != client) {
            try {
                if (!client.isConnected()) {
                    client.connect(options);
                }
                client.subscribe(subTopic, 1);
                log.info("reconnect and sub ok");
                return true;
            } catch (Exception e) {
                log.error("reconnect failed");
                e.printStackTrace();
                return false;
            }
        } else {
            return connect();
        }
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public void setMQID(String MQID) {
        this.MQID = MQID;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setHandler(MessageHandler handler) {
        this.handler = handler;
    }
}
