package com.gridweaver.service;

import java.util.concurrent.ExecutorService;

import org.springframework.stereotype.Service;

@Service
public class BatteryTelemetryService {

    private final ExecutorService virtualThreadExecutor;

    public BatteryTelemetryService(ExecutorService virtualThreadExecutor) {
        this.virtualThreadExecutor = virtualThreadExecutor;
    }

    public void processBatteryTelemetry(Long batteryId) {

        virtualThreadExecutor.submit(() -> {

            System.out.println(
                    "Processing telemetry for Battery ID: " + batteryId
                    + " on " + Thread.currentThread());

        });

    }
}