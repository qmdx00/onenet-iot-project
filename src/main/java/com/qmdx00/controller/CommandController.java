package com.qmdx00.controller;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanweimin
 * @date 19/06/21 09:52
 * @description 设备控制，命令下发 Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/cmd")
public class CommandController {

    private final OkHttpClient client;

    @Autowired
    public CommandController(OkHttpClient client) {
        this.client = client;
    }

    
}
