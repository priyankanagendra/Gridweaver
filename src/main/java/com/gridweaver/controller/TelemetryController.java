package com.gridweaver.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gridweaver.dto.GridNodeUpdateDTO;
import com.gridweaver.kafka.TelemetryKafkaProducer;
import com.gridweaver.service.BatteryTelemetryService;
import com.gridweaver.service.StateMachineService;

@RestController
public class TelemetryController {

    private final BatteryTelemetryService batteryTelemetryService;
    private final StateMachineService stateMachineService;
    private final TelemetryKafkaProducer telemetryKafkaProducer;


    public TelemetryController(
            BatteryTelemetryService batteryTelemetryService,
            StateMachineService stateMachineService,
            TelemetryKafkaProducer telemetryKafkaProducer) {

        this.batteryTelemetryService = batteryTelemetryService;
        this.stateMachineService = stateMachineService;
        this.telemetryKafkaProducer = telemetryKafkaProducer;
    }


    @PostMapping("/telemetry/{batteryId}")
    public String processTelemetry(
            @PathVariable Long batteryId) {

        batteryTelemetryService
                .processBatteryTelemetry(batteryId);


        stateMachineService
                .printCurrentState(batteryId);


        Long nodeId =
                ((batteryId - 1) % 4) + 1;


        GridNodeUpdateDTO update =
                new GridNodeUpdateDTO(
                        nodeId,
                        "WARNING",
                        75.0,
                        82.0,
                        35.0
                );


        telemetryKafkaProducer
                .sendTelemetry(update);


        return "Telemetry received for Battery ID: "
                + batteryId;
    }
}