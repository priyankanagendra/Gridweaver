package com.gridweaver.service;
import com.gridweaver.exception.BatteryNotFoundException;
import com.gridweaver.dto.BatteryDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gridweaver.entity.Battery;
import com.gridweaver.repository.BatteryRepository;
import com.gridweaver.service.BatteryService;

@Service
public class BatteryServiceImpl implements BatteryService {

	private final BatteryRepository batteryRepository;

	public BatteryServiceImpl(BatteryRepository batteryRepository) {
	    this.batteryRepository = batteryRepository;
	}
    @Override
    public BatteryDTO saveBattery(BatteryDTO batteryDTO) {

        Battery battery = convertToEntity(batteryDTO);

        Battery savedBattery = batteryRepository.save(battery);

        return convertToDTO(savedBattery);
    }
    @Override
    public List<BatteryDTO> getAllBatteries() {

        List<Battery> batteries = batteryRepository.findAll();

        List<BatteryDTO> batteryDTOs = new java.util.ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }
    
    @Override
    public List<BatteryDTO> getBatteriesByType(String batteryType) {

        List<Battery> batteries = batteryRepository.findByBatteryType(batteryType);

        List<BatteryDTO> batteryDTOs = new java.util.ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }
    
    @Override
    public BatteryDTO updateBattery(Long id, Battery updatedBattery) {

    	Battery existingBattery = batteryRepository.findById(id)
    	        .orElseThrow(() ->
    	                new BatteryNotFoundException("Battery not found with ID: " + id));

    	existingBattery.setBatteryName(updatedBattery.getBatteryName());
    	existingBattery.setBatteryType(updatedBattery.getBatteryType());
    	existingBattery.setCapacity(updatedBattery.getCapacity());
    	existingBattery.setVoltage(updatedBattery.getVoltage());

    	Battery savedBattery = batteryRepository.save(existingBattery);

    	return convertToDTO(savedBattery);
    }
    @Override
    public void deleteBattery(Long id) {

    	Battery battery = batteryRepository.findById(id)
    	        .orElseThrow(() ->
    	                new BatteryNotFoundException("Battery not found with ID: " + id));

    	batteryRepository.delete(battery);
    }
    @Override
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
    private Battery convertToEntity(BatteryDTO dto) {

        Battery battery = new Battery();

        battery.setId(dto.getId());
        battery.setBatteryName(dto.getBatteryName());
        battery.setBatteryType(dto.getBatteryType());
        battery.setCapacity(dto.getCapacity());
        battery.setVoltage(dto.getVoltage());

        return battery;
    }
}