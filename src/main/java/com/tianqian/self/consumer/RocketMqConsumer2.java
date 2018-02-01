package com.tianqian.self.consumer;

import com.qianmi.ms.starter.rocketmq.annotation.RocketMQMessageListener;
import com.qianmi.ms.starter.rocketmq.core.RocketMQListener;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(topic = "zjx", consumerGroup = "zjx-test")
public class RocketMqConsumer2 implements RocketMQListener<String> {
    private final Logger logger = LoggerFactory.getLogger(RocketMqConsumer2.class);

    @Override
    public void onMessage(String message) {
        logger.info("received message: {}", message);
    }
}
