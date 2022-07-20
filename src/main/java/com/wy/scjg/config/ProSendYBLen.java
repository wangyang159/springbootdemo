package com.wy.scjg.config;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

//@Configuration
public class ProSendYBLen {
    private final static Logger logger = LoggerFactory.getLogger(KafkaListener.class);

    @Resource
    KafkaTemplate kafkaTemplate;

    //配置监听
    @PostConstruct
    private void listener() {
        kafkaTemplate.setProducerListener(new ProducerListener() {
            @Override
            public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
                logger.info("我已经接收到消息-----message={}", producerRecord.value());

            }

            @Override
            public void onError(ProducerRecord producerRecord, RecordMetadata recordMetadata, Exception exception) {
                logger.error("接收失败--------message={}", producerRecord.value());
            }
        });
    }
}
