package com.example.demokafka.kafka.producer;

import com.example.demokafka.kafka.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, Message> kafkaTemplate;

    @Value(value = "${kafka.topic.name}")
    private String topic;


    public void sendMessage(Message message) {

        ListenableFuture<SendResult<String, Message>> future = kafkaTemplate.send(this.topic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Message>>(){

            @Override
            public void onSuccess(SendResult<String, Message> result) {
                log.info(result.getProducerRecord().value().getMessage());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error(ex.getMessage());
            }
        });
    }
}