package com.gridweaver.service;
import com.gridweaver.exception.BatteryNotFoundException;
import com.gridweaver.dto.BatteryDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import com.gridweaver.entity.Battery;
import com.gridweaver.repository.BatteryRepository;
import com.gridweaver.service.BatteryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
    public List<BatteryDTO> getAllBatteriesSortedByCapacity() {

        List<Battery> batteries =
                batteryRepository.findAll(Sort.by("capacity"));

        List<BatteryDTO> batteryDTOs = new java.util.ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }
    
    @Override
    public List<BatteryDTO> getAllBatteriesSortedByCapacityDesc() {

        List<Battery> batteries =
                batteryRepository.findAll(Sort.by(Sort.Direction.DESC, "capacity"));

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
    public List<BatteryDTO> getBatteriesByName(String batteryName) {

        List<Battery> batteries = batteryRepository.findByBatteryName(batteryName);

        List<BatteryDTO> batteryDTOs = new java.util.ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }
    
    @Override
    public List<BatteryDTO> getBatteriesByCapacityGreaterThan(Double capacity) {

        List<Battery> batteries =
                batteryRepository.findByCapacityGreaterThan(capacity);

        List<BatteryDTO> batteryDTOs = new java.util.ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }
    
    @Override
    public List<BatteryDTO> getBatteriesByCapacityLessThan(Double capacity) {

        List<Battery> batteries =
                batteryRepository.findByCapacityLessThan(capacity);

        List<BatteryDTO> batteryDTOs = new java.util.ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }
    
    @Override
    public List<BatteryDTO> getBatteriesByCapacityBetween(Double minCapacity, Double maxCapacity) {

        List<Battery> batteries =
                batteryRepository.findByCapacityBetween(minCapacity, maxCapacity);

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
    
    @Override
    public List<BatteryDTO> getBatteriesByPage(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Battery> batteryPage =
                batteryRepository.findAll(pageable);

        List<BatteryDTO> batteryDTOs = new java.util.ArrayList<>();

        for (Battery battery : batteryPage.getContent()) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }
    
    @Override
    public List<BatteryDTO> getBatteriesByBatteryNameContaining(String batteryName) {

        List<Battery> batteries =
                batteryRepository.findByBatteryNameContaining(batteryName);

        List<BatteryDTO> batteryDTOs = new java.util.ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }
    
    @Override
    public List<BatteryDTO> getBatteriesByBatteryNameStartingWith(String batteryName) {

        List<Battery> batteries =
                batteryRepository.findByBatteryNameStartingWith(batteryName);

        List<BatteryDTO> batteryDTOs = new java.util.ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }
    
    @Override
    public List<BatteryDTO> getBatteriesByBatteryNameEndingWith(String batteryName) {

        List<Battery> batteries =
                batteryRepository.findByBatteryNameEndingWith(batteryName);

        List<BatteryDTO> batteryDTOs = new java.util.ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }
    
    @Override
    public List<BatteryDTO> getBatteriesByBatteryNameContainingIgnoreCase(String batteryName) {

        List<Battery> batteries =
                batteryRepository.findByBatteryNameContainingIgnoreCase(batteryName);

        List<BatteryDTO> batteryDTOs = new java.util.ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }
    
    @Override
    public List<BatteryDTO> getBatteriesByBatteryTypeOrderByCapacityAsc(String batteryType) {

        List<Battery> batteries =
                batteryRepository.findByBatteryTypeOrderByCapacityAsc(batteryType);

        List<BatteryDTO> batteryDTOs = new java.util.ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
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