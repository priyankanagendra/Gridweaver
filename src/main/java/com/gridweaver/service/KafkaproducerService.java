package com.gridweaver.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaproducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaproducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("battery-topic", message);
        System.out.println("Message Sent : " + message);
    }
}