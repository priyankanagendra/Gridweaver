package com.gridweaver.controller;

import java.util.List;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gridweaver.entity.Battery;
import com.gridweaver.service.BatteryService;
import com.gridweaver.dto.BatteryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/battery")
public class BatteryController {
	
	public BatteryController() {
	    System.out.println("BatteryController Loaded");
	}

    @Autowired
    private BatteryService batteryService;

    @PostMapping
    public ResponseEntity<BatteryDTO> saveBattery(@Valid @RequestBody BatteryDTO batteryDTO) {

        BatteryDTO savedBattery = batteryService.saveBattery(batteryDTO);

        return new ResponseEntity<>(savedBattery, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BatteryDTO>> getAllBatteries() {

        List<BatteryDTO> batteries = batteryService.getAllBatteries();

        return ResponseEntity.ok(batteries);
    }
    
    @GetMapping("/type/{batteryType}")
    public ResponseEntity<List<BatteryDTO>> getBatteriesByType(
            @PathVariable String batteryType) {

        List<BatteryDTO> batteries =
                batteryService.getBatteriesByType(batteryType);

        return ResponseEntity.ok(batteries);
    }
    
    @GetMapping("/name/{batteryName}")
    public ResponseEntity<List<BatteryDTO>> getBatteriesByName(
            @PathVariable String batteryName) {

        List<BatteryDTO> batteries =
                batteryService.getBatteriesByName(batteryName);

        return ResponseEntity.ok(batteries);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<BatteryDTO> updateBattery(@PathVariable Long id,
                                                    @RequestBody Battery battery) {

        BatteryDTO updatedBattery = batteryService.updateBattery(id, battery);

        return ResponseEntity.ok(updatedBattery);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBattery(@PathVariable Long id) {

        batteryService.deleteBattery(id);

        return ResponseEntity.ok("Battery deleted successfully.");
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BatteryDTO> getBatteryById(@PathVariable Long id) {

        BatteryDTO battery = batteryService.getBatteryById(id);

        return ResponseEntity.ok(battery);
    }
    }
