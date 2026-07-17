package com.gridweaver.service;

import java.util.List;

import com.gridweaver.dto.BatteryDTO;
import com.gridweaver.entity.Battery;


public interface BatteryService {

    BatteryDTO saveBattery(BatteryDTO batteryDTO);

    List<BatteryDTO> getAllBatteries();

    BatteryDTO updateBattery(Long id, Battery updatedBattery);

    void deleteBattery(Long id);

    BatteryDTO getBatteryById(Long id);
}