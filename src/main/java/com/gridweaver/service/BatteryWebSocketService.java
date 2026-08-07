package com.gridweaver.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class BatteryWebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public BatteryWebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendBatteryUpdate(Object battery) {
        messagingTemplate.convertAndSend("/topic/batteries", battery);
    }

    public void sendMessage(String message) {
        messagingTemplate.convertAndSend("/topic/messages", message);
    }
}