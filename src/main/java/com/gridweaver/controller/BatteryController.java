package com.gridweaver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gridweaver.entity.Battery;
import com.gridweaver.service.BatteryService;

@RestController
@RequestMapping("/battery")
public class BatteryController {

    @Autowired
    private BatteryService batteryService;

    @PostMapping
    public Battery saveBattery(@RequestBody Battery battery) {
        return batteryService.saveBattery(battery);
    }

    @GetMapping
    public List<Battery> getAllBatteries() {
        return batteryService.getAllBatteries();
    }
}