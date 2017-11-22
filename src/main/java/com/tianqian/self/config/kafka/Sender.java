//package com.tianqian.self.config.kafka;
//
//import com.alibaba.fastjson.JSON;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class Sender {
//
//    @Autowired
//    private KafkaTemplate kafkaTemplate;
//
//    public void sendMessage(){
//        Message message = new Message();
//        message.setId(System.currentTimeMillis());
//        message.setMsg("来自kafka的第一条问候");
//        message.setSendTime(new Date());
//        String msg = JSON.toJSONString(message);
//        kafkaTemplate.send("test", msg);
//    }
//}
