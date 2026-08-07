package com.gridweaver.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.gridweaver.dto.GridNodeUpdateDTO;

@Service
public class GridUpdateService {

    private final SimpMessagingTemplate messagingTemplate;

    public GridUpdateService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendGridUpdate(GridNodeUpdateDTO update) {

        messagingTemplate.convertAndSend(
                "/topic/grid",
                update
        );
    }
}