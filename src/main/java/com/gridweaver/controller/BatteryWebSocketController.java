package com.gridweaver.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class BatteryWebSocketController {

    @MessageMapping("/battery")
    @SendTo("/topic/batteries")
    public String sendBatteryUpdate(String message) {

        return message;

    }

}