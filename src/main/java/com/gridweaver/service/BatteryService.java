package com.gridweaver.service;
import com.gridweaver.exception.BatteryNotFoundException;
import com.gridweaver.dto.BatteryDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gridweaver.entity.Battery;
import com.gridweaver.repository.BatteryRepository;

@Service
public class BatteryService {

    @Autowired
    private BatteryRepository batteryRepository;

    public BatteryDTO saveBattery(Battery battery) {

        Battery savedBattery = batteryRepository.save(battery);

        return convertToDTO(savedBattery);
    }

    public List<BatteryDTO> getAllBatteries() {

        List<Battery> batteries = batteryRepository.findAll();

        List<BatteryDTO> batteryDTOs = new java.util.ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }
    
    public BatteryDTO updateBattery(Long id, Battery updatedBattery) {

        Battery existingBattery = batteryRepository.findById(id).orElse(null);

        if (existingBattery != null) {

            existingBattery.setBatteryName(updatedBattery.getBatteryName());
            existingBattery.setBatteryType(updatedBattery.getBatteryType());
            existingBattery.setCapacity(updatedBattery.getCapacity());
            existingBattery.setVoltage(updatedBattery.getVoltage());

            Battery savedBattery = batteryRepository.save(existingBattery);

            return convertToDTO(savedBattery);
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
    public BatteryDTO getBatteryById(Long id) {

        Battery battery = batteryRepository.findById(id)
                .orElseThrow(() ->
                    new BatteryNotFoundException("Battery not found with ID: " + id));

        return convertToDTO(battery);
    }
    
    private BatteryDTO convertToDTO(Battery battery) {

        BatteryDTO dto = new BatteryDTO();

        dto.setId(battery.getId());
        dto.setBatteryName(battery.getBatteryName());
        dto.setBatteryType(battery.getBatteryType());
        dto.setCapacity(battery.getCapacity());
        dto.setVoltage(battery.getVoltage());

        return dto;
    }
}