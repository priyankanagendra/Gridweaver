package com.gridweaver.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gridweaver.dto.GridNodeUpdateDTO;
import com.gridweaver.service.BatteryTelemetryService;
import com.gridweaver.service.GridUpdateService;
import com.gridweaver.service.StateMachineService;

@RestController
public class TelemetryController {

    private final BatteryTelemetryService batteryTelemetryService;
    private final StateMachineService stateMachineService;
    private final GridUpdateService gridUpdateService;

    public TelemetryController(
            BatteryTelemetryService batteryTelemetryService,
            StateMachineService stateMachineService,
            GridUpdateService gridUpdateService) {

        this.batteryTelemetryService = batteryTelemetryService;
        this.stateMachineService = stateMachineService;
        this.gridUpdateService = gridUpdateService;
    }

    @PostMapping("/telemetry/{batteryId}")
    public String processTelemetry(@PathVariable Long batteryId) {

        batteryTelemetryService.processBatteryTelemetry(batteryId);

        stateMachineService.printCurrentState(batteryId);

        Long nodeId = ((batteryId - 1) % 4) + 1;

        GridNodeUpdateDTO update =
                new GridNodeUpdateDTO(
                        nodeId,
                        "WARNING",
                        75.0
                );

        gridUpdateService.sendGridUpdate(update);

        return "Telemetry received for Battery ID: " + batteryId;
    }
}