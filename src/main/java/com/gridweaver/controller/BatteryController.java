package com.gridweaver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gridweaver.dto.BatteryDTO;
import com.gridweaver.entity.Battery;
import com.gridweaver.service.BatteryService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
@RestController
@RequestMapping("/battery")
@CrossOrigin(origins = "http://localhost:5176")


public class BatteryController {

    @Autowired
    private BatteryService batteryService;

    // Save Battery
    @PostMapping
    public Battery saveBattery(@Valid @RequestBody Battery battery) {
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

    // Get All Batteries using DTO
    @GetMapping("/dto")
    public List<BatteryDTO> getAllBatteryDTOs() {
        return batteryService.getAllBatteryDTOs();
    }

    // Update Battery
    @PutMapping("/{id}")
    public Battery updateBattery(@PathVariable Long id,
                                 @Valid @RequestBody Battery battery) {
        return batteryService.updateBattery(id, battery);
    }

    // Delete Battery
    @DeleteMapping("/{id}")
    public String deleteBattery(@PathVariable Long id) {
        batteryService.deleteBattery(id);
        return "Battery deleted successfully";
    }

    // Virtual Thread Demo
    @GetMapping("/virtual-thread")
    public String processBatteryTask() throws Exception {
        return batteryService.processBatteryTask();
    }

    // Virtual Thread Concurrency Test
    @GetMapping("/concurrency-test")
    public String concurrencyTest() throws Exception {
        return batteryService.concurrencyTest();
    }
}