package com.gridweaver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gridweaver.entity.Battery;

public interface BatteryRepository extends JpaRepository<Battery, Long> {

    List<Battery> findByBatteryType(String batteryType);
    
    List<Battery> findByBatteryName(String batteryName);
    
    List<Battery> findByCapacityGreaterThan(Double capacity);
    
}