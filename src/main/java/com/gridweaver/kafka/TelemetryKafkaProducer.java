package com.gridweaver.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.gridweaver.dto.GridNodeUpdateDTO;

@Service
public class TelemetryKafkaProducer {

    private static final String TOPIC =
            "gridweaver-telemetry";

    private final KafkaTemplate<String, GridNodeUpdateDTO>
            kafkaTemplate;

    public TelemetryKafkaProducer(
            KafkaTemplate<String, GridNodeUpdateDTO> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTelemetry(
            GridNodeUpdateDTO update) {

        kafkaTemplate.send(
                TOPIC,
                String.valueOf(update.getNodeId()),
                update
        );
    }
}