package com.gridweaver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "battery-topic", groupId = "gridweaver-group")
    public void consume(String message) {

        System.out.println("Message Received : " + message);

        // Send the message to all WebSocket clients
        messagingTemplate.convertAndSend("/topic/batteries", message);
    }
}