package com.gridweaver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gridweaver.entity.Battery;

public interface BatteryRepository extends JpaRepository<Battery, Long> {

}