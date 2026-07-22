package com.gridweaver.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gridweaver.service.BatteryTelemetryService;
import com.gridweaver.service.StateMachineService;

@RestController
public class TelemetryController {

    private final BatteryTelemetryService batteryTelemetryService;
    private final StateMachineService stateMachineService;
    
    public TelemetryController(BatteryTelemetryService batteryTelemetryService,
            StateMachineService stateMachineService) {

		this.batteryTelemetryService = batteryTelemetryService;
		this.stateMachineService = stateMachineService;
}

    @PostMapping("/telemetry/{batteryId}")
    public String processTelemetry(@PathVariable Long batteryId) {

        batteryTelemetryService.processBatteryTelemetry(batteryId);

        stateMachineService.printCurrentState();

        return "Telemetry received for Battery ID: " + batteryId;
    }
}