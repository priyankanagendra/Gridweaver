package com.gridweaver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gridweaver.entity.Battery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BatteryRepository extends JpaRepository<Battery, Long> {

    List<Battery> findByBatteryType(String batteryType);
    
    List<Battery> findByBatteryName(String batteryName);
    
    List<Battery> findByCapacityGreaterThan(Double capacity);
    
    List<Battery> findByCapacityLessThan(Double capacity);
    
    List<Battery> findByCapacityBetween(Double minCapacity, Double maxCapacity);
    
    List<Battery> findByBatteryNameContaining(String batteryName);
    
    List<Battery> findByBatteryNameStartingWith(String batteryName);
    
    List<Battery> findByBatteryNameEndingWith(String batteryName);
    
    List<Battery> findByBatteryNameContainingIgnoreCase(String batteryName);
    
    List<Battery> findByBatteryTypeOrderByCapacityAsc(String batteryType);

    @Query("SELECT b FROM Battery b WHERE b.capacity > :capacity")
    List<Battery> findBatteriesWithCapacityGreaterThan(
            @Param("capacity") Double capacity);
    
    
}