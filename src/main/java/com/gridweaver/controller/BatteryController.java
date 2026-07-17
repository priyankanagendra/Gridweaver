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
    
    @GetMapping("/capacity/{capacity}")
    public ResponseEntity<List<BatteryDTO>> getBatteriesByCapacityGreaterThan(
            @PathVariable Double capacity) {

        List<BatteryDTO> batteries =
                batteryService.getBatteriesByCapacityGreaterThan(capacity);

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
    
    @GetMapping("/sorted/capacity")
    public ResponseEntity<List<BatteryDTO>> getAllBatteriesSortedByCapacity() {

        List<BatteryDTO> batteries =
                batteryService.getAllBatteriesSortedByCapacity();

        return ResponseEntity.ok(batteries);
    }
    
    @GetMapping("/sorted/capacity/desc")
    public ResponseEntity<List<BatteryDTO>> getAllBatteriesSortedByCapacityDesc() {

        List<BatteryDTO> batteries =
                batteryService.getAllBatteriesSortedByCapacityDesc();

        return ResponseEntity.ok(batteries);
    }
    
    @GetMapping("/page")
    public ResponseEntity<List<BatteryDTO>> getBatteriesByPage(
            @RequestParam int page,
            @RequestParam int size) {

        List<BatteryDTO> batteries =
                batteryService.getBatteriesByPage(page, size);

        return ResponseEntity.ok(batteries);
    }
    
    @GetMapping("/capacity/less/{capacity}")
    public ResponseEntity<List<BatteryDTO>> getBatteriesByCapacityLessThan(
            @PathVariable Double capacity) {

        List<BatteryDTO> batteries =
                batteryService.getBatteriesByCapacityLessThan(capacity);

        return ResponseEntity.ok(batteries);
    }
    
    @GetMapping("/capacity/between/{minCapacity}/{maxCapacity}")
    public ResponseEntity<List<BatteryDTO>> getBatteriesByCapacityBetween(
            @PathVariable Double minCapacity,
            @PathVariable Double maxCapacity) {

        List<BatteryDTO> batteries =
                batteryService.getBatteriesByCapacityBetween(minCapacity, maxCapacity);

        return ResponseEntity.ok(batteries);
    }
    
    @GetMapping("/search/{batteryName}")
    public ResponseEntity<List<BatteryDTO>> getBatteriesByBatteryNameContaining(
            @PathVariable String batteryName) {

        List<BatteryDTO> batteries =
                batteryService.getBatteriesByBatteryNameContaining(batteryName);

        return ResponseEntity.ok(batteries);
    }
    
    @GetMapping("/search/start/{batteryName}")
    public ResponseEntity<List<BatteryDTO>> getBatteriesByBatteryNameStartingWith(
            @PathVariable String batteryName) {

        List<BatteryDTO> batteries =
                batteryService.getBatteriesByBatteryNameStartingWith(batteryName);

        return ResponseEntity.ok(batteries);
    }
    
    @GetMapping("/search/end/{batteryName}")
    public ResponseEntity<List<BatteryDTO>> getBatteriesByBatteryNameEndingWith(
            @PathVariable String batteryName) {

        List<BatteryDTO> batteries =
                batteryService.getBatteriesByBatteryNameEndingWith(batteryName);

        return ResponseEntity.ok(batteries);
    }
    
    @GetMapping("/search/ignorecase/{batteryName}")
    public ResponseEntity<List<BatteryDTO>> getBatteriesByBatteryNameContainingIgnoreCase(
            @PathVariable String batteryName) {

        List<BatteryDTO> batteries =
                batteryService.getBatteriesByBatteryNameContainingIgnoreCase(batteryName);

        return ResponseEntity.ok(batteries);
    }
    
    @GetMapping("/search/type/{batteryType}/capacity/asc")
    public ResponseEntity<List<BatteryDTO>> getBatteriesByBatteryTypeOrderByCapacityAsc(
            @PathVariable String batteryType) {

        List<BatteryDTO> batteries =
                batteryService.getBatteriesByBatteryTypeOrderByCapacityAsc(batteryType);

        return ResponseEntity.ok(batteries);
    }
    
    @GetMapping("/jpql/capacity/{capacity}")
    public ResponseEntity<List<BatteryDTO>> findBatteriesWithCapacityGreaterThan(
            @PathVariable Double capacity) {

        List<BatteryDTO> batteries =
                batteryService.findBatteriesWithCapacityGreaterThan(capacity);

        return ResponseEntity.ok(batteries);
    }
    
    
    
    }
