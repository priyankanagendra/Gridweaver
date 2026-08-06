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

    // Save Battery
    @PostMapping
    public Battery saveBattery(@RequestBody Battery battery) {
        return batteryService.saveBattery(battery);
    }

    // Get All Batteries
    @GetMapping
    public List<Battery> getAllBatteries() {
        return batteryService.getAllBatteries();
    }

    // Get Battery By Id
    @GetMapping("/{id}")
    public Battery getBatteryById(@PathVariable Long id) {
        return batteryService.getBatteryById(id);
    }

    // Update Battery
    @PutMapping("/{id}")
    public Battery updateBattery(@PathVariable Long id, @RequestBody Battery battery) {
        return batteryService.updateBattery(id, battery);
    }

    // Delete Battery
    @DeleteMapping("/{id}")
    public String deleteBattery(@PathVariable Long id) {
        batteryService.deleteBattery(id);
        return "Battery deleted successfully";
    }

    // Virtual Thread Demo
    @GetMapping("/process")
    public String processBatteryTask() throws Exception {
        return batteryService.processBatteryTask();
    }

    // Virtual Thread Concurrency Test
    @GetMapping("/concurrency")
    public String concurrencyTest() throws Exception {
        return batteryService.concurrencyTest();
    }
}