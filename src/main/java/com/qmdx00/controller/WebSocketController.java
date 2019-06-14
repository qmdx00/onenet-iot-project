package com.qmdx00.controller;

import com.qmdx00.util.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

//    private final SimpMessagingTemplate template;
//
//    @Autowired
//    public WebSocketController(SimpMessagingTemplate template) {
//        this.template = template;
//    }
//
//    // 接收 /app/msg 发来的消息，转发给订阅 /topic/msg 客户端
//    @MessageMapping("msg")
////    @SendTo("topic/msg")
//    public void message(Message str) {
//        System.out.println(str);
//        template.convertAndSend("/topic/msg", str);
////        return str.toString();
//    }
}
