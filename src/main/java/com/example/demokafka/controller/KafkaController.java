package com.example.demokafka.controller;

import com.example.demokafka.kafka.Message;
import com.example.demokafka.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaController {
    private final KafkaProducer kafkaProducer;
    @GetMapping("/messages/send")
    public ResponseEntity<Message> sendMessage(@RequestParam("message") String message) {
        kafkaProducer.sendMessage(new Message(message));
        return ResponseEntity.ok(new Message(message));
    }
}