package com.gridweaver.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gridweaver.service.BatteryTelemetryService;

@RestController
public class TelemetryController {

    private final BatteryTelemetryService batteryTelemetryService;

    public TelemetryController(BatteryTelemetryService batteryTelemetryService) {
        this.batteryTelemetryService = batteryTelemetryService;
    }

    @PostMapping("/telemetry/{batteryId}")
    public String processTelemetry(@PathVariable Long batteryId) {

        batteryTelemetryService.processBatteryTelemetry(batteryId);

        return "Telemetry received for Battery ID: " + batteryId;
    }
}