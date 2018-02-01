package com.tianqian.self.consumer;

import com.qianmi.ms.starter.rocketmq.annotation.RocketMQMessageListener;
import com.qianmi.ms.starter.rocketmq.core.RocketMQListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(topic = "zjx", consumerGroup = "zjx-test")
public class RocketMqConsumer implements RocketMQListener<MessageExt> {
    private final Logger logger = LoggerFactory.getLogger(RocketMqConsumer.class);

    @Override
    public void onMessage(MessageExt messageExt) {
        logger.info("received messageExt: {}", messageExt);
        System.out.println("---------"+ new String(messageExt.getBody())+"---------");
    }
}
