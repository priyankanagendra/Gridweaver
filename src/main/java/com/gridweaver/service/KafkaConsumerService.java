package com.gridweaver.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "battery-topic", groupId = "gridweaver-group")
    public void consume(String message) {

        System.out.println("Message Received : " + message);

    }
}