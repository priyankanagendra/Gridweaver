package com.gridweaver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gridweaver.entity.Battery;
import com.gridweaver.repository.BatteryRepository;

@Service
public class BatteryService {

    @Autowired
    private BatteryRepository batteryRepository;

    public Battery saveBattery(Battery battery) {
        return batteryRepository.save(battery);
    }

    public List<Battery> getAllBatteries() {
        return batteryRepository.findAll();
    }
}