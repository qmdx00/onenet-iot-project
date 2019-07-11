package com.qmdx00;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yuanweimin
 * @date 19/06/10 10:25
 * @description 项目启动类
 */
@EnableAsync
@SpringBootApplication
@EnableTransactionManagement
public class IotApplication {
    public static void main(String[] args) {
        SpringApplication.run(IotApplication.class);
    }
}
