package com.qmdx00.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author yuanweimin
 * @date 19/06/24 09:29
 * @description okHttp 工具类
 */
@Slf4j
@Component
@PropertySource("classpath:config/mqClient-config.properties")
public class OkHttpUtil {

    private final Environment env;
    private final OkHttpClient client;

    @Autowired
    public OkHttpUtil(OkHttpClient client, Environment env) {
        this.client = client;
        this.env = env;
    }

    /**
     * Post请求发送JSON数据
     *
     * @param url        请求地址
     * @param jsonParams json字符串
     * @return String
     */
    public String postJson(String url, String jsonParams) {
        String responseBody = "";

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Host", "api.heclouds.com")
                .addHeader("api-key", Objects.requireNonNull(env.getProperty("machine.apiKey")))
                .build();

        System.out.println(new String(responseBody.getBytes()));

        try (Response response = this.client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return Objects.requireNonNull(response.body()).string();
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
        return responseBody;
    }
}
