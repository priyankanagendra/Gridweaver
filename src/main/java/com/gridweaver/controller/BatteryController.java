package com.gridweaver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gridweaver.entity.Battery;
import com.gridweaver.service.BatteryService;
import com.gridweaver.dto.BatteryDTO;

@RestController
@RequestMapping("/battery")
public class BatteryController {
	
	public BatteryController() {
	    System.out.println("BatteryController Loaded");
	}

    @Autowired
    private BatteryService batteryService;

    @PostMapping
    public BatteryDTO saveBattery(@RequestBody Battery battery) {
        return batteryService.saveBattery(battery);
    }

    @GetMapping
    public List<BatteryDTO> getAllBatteries() {
        return batteryService.getAllBatteries();
    }
    
    @PutMapping("/{id}")
    public BatteryDTO updateBattery(@PathVariable Long id,
                                 @RequestBody Battery battery) {

        return batteryService.updateBattery(id, battery);
    }
    
    @DeleteMapping("/{id}")
    public String deleteBattery(@PathVariable Long id) {
        batteryService.deleteBattery(id);
        return "Battery deleted successfully.";
    }
    
    @GetMapping("/{id}")
    public BatteryDTO getBatteryById(@PathVariable Long id) {
        return batteryService.getBatteryById(id);
    }
    }
