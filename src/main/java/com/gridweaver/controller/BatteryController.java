package com.gridweaver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gridweaver.dto.BatteryDTO;
import com.gridweaver.entity.Battery;
import com.gridweaver.service.BatteryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/battery")
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
    
 // Virtual Thread API
    @GetMapping("/virtual-thread")
    public String processBatteryTask() throws Exception {
        return batteryService.processBatteryTask();
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
    @GetMapping("/concurrency-test")
    public String concurrencyTest() throws Exception {
        return batteryService.concurrencyTest();
    }
}



		