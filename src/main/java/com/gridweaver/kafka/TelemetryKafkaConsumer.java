package com.gridweaver.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.gridweaver.dto.GridNodeUpdateDTO;
import com.gridweaver.service.GridUpdateService;

@Service
public class TelemetryKafkaConsumer {

    private final GridUpdateService gridUpdateService;

    public TelemetryKafkaConsumer(
            GridUpdateService gridUpdateService) {

        this.gridUpdateService = gridUpdateService;
    }

    @KafkaListener(
            topics = "gridweaver-telemetry",
            groupId = "gridweaver-group"
    )
    public void consumeTelemetry(
            GridNodeUpdateDTO update) {

        System.out.println(
                "Kafka telemetry received for node: "
                        + update.getNodeId()
        );

        gridUpdateService.sendGridUpdate(update);
    }
}