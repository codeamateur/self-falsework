//package com.tianqian.self.config.kafka;
//
//import com.alibaba.fastjson.JSON;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Receiver {
//
//    @KafkaListener(topics = "test")
//    public void processMessage(String content) {
//        Message m = JSON.parseObject(content, Message.class);
//        System.out.println(JSON.toJSON(m));
//    }
//}
