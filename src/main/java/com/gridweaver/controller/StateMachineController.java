package com.gridweaver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gridweaver.service.StateMachineService;

@RestController
public class StateMachineController {

    @Autowired
    private StateMachineService stateMachineService;

    @GetMapping("/state/charge")
    public String chargeBattery() {
        return stateMachineService.startCharging();
    }

    @GetMapping("/state/discharge")
    public String dischargeBattery() {
        return stateMachineService.startDischarging();
    }

    @GetMapping("/state/stop")
    public String stopBattery() {
        return stateMachineService.stopBattery();
    }

    @GetMapping("/state/error")
    public String batteryError() {
        return stateMachineService.batteryError();
    }
}