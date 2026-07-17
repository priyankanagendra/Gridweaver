package com.gridweaver.service;

import java.util.List;

import com.gridweaver.dto.BatteryDTO;
import com.gridweaver.entity.Battery;


public interface BatteryService {

    BatteryDTO saveBattery(BatteryDTO batteryDTO);

    List<BatteryDTO> getAllBatteries();
    
    List<BatteryDTO> getBatteriesByType(String batteryType);
    
    List<BatteryDTO> getBatteriesByName(String batteryName);
    
    List<BatteryDTO> getBatteriesByCapacityGreaterThan(Double capacity);
    
    List<BatteryDTO> getAllBatteriesSortedByCapacity();
    
    List<BatteryDTO> getAllBatteriesSortedByCapacityDesc();
    
    List<BatteryDTO> getBatteriesByPage(int page, int size);
    
    List<BatteryDTO> getBatteriesByCapacityLessThan(Double capacity);
    
    List<BatteryDTO> getBatteriesByCapacityBetween(Double minCapacity, Double maxCapacity);
    
    List<BatteryDTO> getBatteriesByBatteryNameContaining(String batteryName);
    
    List<BatteryDTO> getBatteriesByBatteryNameStartingWith(String batteryName);
    
    List<BatteryDTO> getBatteriesByBatteryNameEndingWith(String batteryName);
    
    List<BatteryDTO> getBatteriesByBatteryNameContainingIgnoreCase(String batteryName);
    
    BatteryDTO updateBattery(Long id, Battery updatedBattery);

    void deleteBattery(Long id);

    BatteryDTO getBatteryById(Long id);
}