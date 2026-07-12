package com.gridweaver.service;
import com.gridweaver.exception.BatteryNotFoundException;

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
    
    public Battery updateBattery(Long id, Battery updatedBattery) {

        Battery existingBattery = batteryRepository.findById(id).orElse(null);

        if (existingBattery != null) {

            existingBattery.setBatteryName(updatedBattery.getBatteryName());
            existingBattery.setBatteryType(updatedBattery.getBatteryType());
            existingBattery.setCapacity(updatedBattery.getCapacity());
            existingBattery.setVoltage(updatedBattery.getVoltage());

            return batteryRepository.save(existingBattery);
        }

        throw new BatteryNotFoundException("Battery not found with ID: " + id);
    }
    
    public void deleteBattery(Long id) {

        Battery battery = batteryRepository.findById(id).orElse(null);

        if (battery != null) {
            batteryRepository.deleteById(id);
        } else {
            throw new BatteryNotFoundException("Battery not found with ID: " + id);
        }
    }
    public Battery getBatteryById(Long id) {

        return batteryRepository.findById(id)
                .orElseThrow(() ->
                    new BatteryNotFoundException("Battery not found with ID: " + id));
    }
}