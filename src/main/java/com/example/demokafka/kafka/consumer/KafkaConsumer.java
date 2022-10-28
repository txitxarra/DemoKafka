package com.example.demokafka.kafka.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaConsumer {


    @KafkaListener(topics = "${kafka.topic.name}")
    public void listener(String message) {
        log.info("Message received {} ", message);
    }
}
