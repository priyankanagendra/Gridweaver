package com.gridweaver.service;

import com.gridweaver.dto.BatteryDTO;
import com.gridweaver.entity.Battery;
import com.gridweaver.entity.Customer;
import com.gridweaver.exception.BatteryNotFoundException;
import com.gridweaver.repository.BatteryRepository;
import com.gridweaver.repository.CustomerRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BatteryServiceImpl implements BatteryService {

    private static final Logger logger =
            LoggerFactory.getLogger(BatteryServiceImpl.class);

    private final BatteryRepository batteryRepository;
    private final CustomerRepository customerRepository;
    private final ExecutorService virtualThreadExecutor;

    public BatteryServiceImpl(
            BatteryRepository batteryRepository,
            CustomerRepository customerRepository,
            ExecutorService virtualThreadExecutor) {

        this.batteryRepository = batteryRepository;
        this.customerRepository = customerRepository;
        this.virtualThreadExecutor = virtualThreadExecutor;
    }

    @Override
    public BatteryDTO saveBattery(BatteryDTO batteryDTO) {

        logger.info("Saving battery: {}", batteryDTO.getBatteryName());

        Battery battery = convertToEntity(batteryDTO);

        Battery savedBattery = batteryRepository.save(battery);

        logger.info("Battery saved successfully with ID: {}",
                savedBattery.getId());

        return convertToDTO(savedBattery);
    }

    @Override
    public List<BatteryDTO> getAllBatteries() {

        logger.info("Fetching all batteries from database");

        List<Battery> batteries = batteryRepository.findAll();

        logger.info("Found {} batteries in database",
                batteries.size());

        List<BatteryDTO> batteryDTOs = new ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }

    @Override
    public List<BatteryDTO> getAllBatteriesSortedByCapacity() {

        List<Battery> batteries =
                batteryRepository.findAll(
                        Sort.by("capacity"));

        List<BatteryDTO> batteryDTOs = new ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }

    @Override
    public List<BatteryDTO> getAllBatteriesSortedByCapacityDesc() {

        List<Battery> batteries =
                batteryRepository.findAll(
                        Sort.by(
                                Sort.Direction.DESC,
                                "capacity"));

        List<BatteryDTO> batteryDTOs = new ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }

    @Override
    public List<BatteryDTO> getBatteriesByType(String batteryType) {

        List<Battery> batteries =
                batteryRepository.findByBatteryType(
                        batteryType);

        List<BatteryDTO> batteryDTOs = new ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }

    @Override
    public List<BatteryDTO> getBatteriesByName(String batteryName) {

        List<Battery> batteries =
                batteryRepository.findByBatteryName(
                        batteryName);

        List<BatteryDTO> batteryDTOs = new ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }

    @Override
    public List<BatteryDTO> getBatteriesByCapacityGreaterThan(
            Double capacity) {

        List<Battery> batteries =
                batteryRepository.findByCapacityGreaterThan(
                        capacity);

        List<BatteryDTO> batteryDTOs = new ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }

    @Override
    public List<BatteryDTO> getBatteriesByCapacityLessThan(
            Double capacity) {

        List<Battery> batteries =
                batteryRepository.findByCapacityLessThan(
                        capacity);

        List<BatteryDTO> batteryDTOs = new ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }

    @Override
    public List<BatteryDTO> getBatteriesByCapacityBetween(
            Double minCapacity,
            Double maxCapacity) {

        List<Battery> batteries =
                batteryRepository.findByCapacityBetween(
                        minCapacity,
                        maxCapacity);

        List<BatteryDTO> batteryDTOs = new ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }
    
    @Override
    public BatteryDTO updateBattery(Long id, Battery updatedBattery) {

        Battery existingBattery = batteryRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Battery not found with ID: {}", id);
                    return new BatteryNotFoundException(
                            "Battery not found with ID: " + id);
                });

        existingBattery.setBatteryName(updatedBattery.getBatteryName());
        existingBattery.setBatteryType(updatedBattery.getBatteryType());
        existingBattery.setCapacity(updatedBattery.getCapacity());
        existingBattery.setVoltage(updatedBattery.getVoltage());

        Battery savedBattery = batteryRepository.save(existingBattery);

        logger.info("Battery updated successfully with ID: {}",
                savedBattery.getId());

        return convertToDTO(savedBattery);
    }

    @Override
    public void deleteBattery(Long id) {

        Battery battery = batteryRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn(
                            "Attempt to delete non-existing battery with ID: {}",
                            id);
                    return new BatteryNotFoundException(
                            "Battery not found with ID: " + id);
                });

        batteryRepository.delete(battery);

        logger.info("Battery deleted successfully with ID: {}", id);
    }

    @Override
    public BatteryDTO getBatteryById(Long id) {

        Battery battery = batteryRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Battery lookup failed for ID: {}", id);
                    return new BatteryNotFoundException(
                            "Battery not found with ID: " + id);
                });

        logger.info("Battery retrieved successfully with ID: {}", id);

        return convertToDTO(battery);
    }

    @Override
    public List<BatteryDTO> getBatteriesByPage(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Battery> batteryPage =
                batteryRepository.findAll(pageable);

        List<BatteryDTO> batteryDTOs = new ArrayList<>();

        for (Battery battery : batteryPage.getContent()) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }

    @Override
    public List<BatteryDTO> getBatteriesByBatteryNameContaining(
            String batteryName) {

        List<Battery> batteries =
                batteryRepository.findByBatteryNameContaining(
                        batteryName);

        List<BatteryDTO> batteryDTOs = new ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }

    @Override
    public List<BatteryDTO> getBatteriesByBatteryNameStartingWith(
            String batteryName) {

        List<Battery> batteries =
                batteryRepository.findByBatteryNameStartingWith(
                        batteryName);

        List<BatteryDTO> batteryDTOs = new ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }

    @Override
    public List<BatteryDTO> getBatteriesByBatteryNameEndingWith(
            String batteryName) {

        List<Battery> batteries =
                batteryRepository.findByBatteryNameEndingWith(
                        batteryName);

        List<BatteryDTO> batteryDTOs = new ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }

    @Override
    public List<BatteryDTO> getBatteriesByBatteryNameContainingIgnoreCase(
            String batteryName) {

        List<Battery> batteries =
                batteryRepository.findByBatteryNameContainingIgnoreCase(
                        batteryName);

        List<BatteryDTO> batteryDTOs = new ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }

    @Override
    public List<BatteryDTO> getBatteriesByBatteryTypeOrderByCapacityAsc(
            String batteryType) {

        List<Battery> batteries =
                batteryRepository.findByBatteryTypeOrderByCapacityAsc(
                        batteryType);

        List<BatteryDTO> batteryDTOs = new ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }

    @Override
    public List<BatteryDTO> findBatteriesWithCapacityGreaterThan(
            Double capacity) {

        List<Battery> batteries =
                batteryRepository.findBatteriesWithCapacityGreaterThan(
                        capacity);

        List<BatteryDTO> batteryDTOs = new ArrayList<>();

        for (Battery battery : batteries) {
            batteryDTOs.add(convertToDTO(battery));
        }

        return batteryDTOs;
    }

    @Override
    public List<BatteryDTO> findBatteriesByCapacityNative(
            Double capacity) {

        List<Battery> batteries =
                batteryRepository.findBatteriesByCapacityNative(
                        capacity);

        List<BatteryDTO> batteryDTOs = new ArrayList<>();

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

        // Added for State Machine
        dto.setState(battery.getState());

        if (battery.getCustomer() != null) {
            dto.setCustomerId(battery.getCustomer().getId());
        }

        return dto;
    }

    private Battery convertToEntity(BatteryDTO dto) {

        Battery battery = new Battery();

        battery.setId(dto.getId());
        battery.setBatteryName(dto.getBatteryName());
        battery.setBatteryType(dto.getBatteryType());
        battery.setCapacity(dto.getCapacity());
        battery.setVoltage(dto.getVoltage());

        // Added for State Machine
        battery.setState(dto.getState());

        if (dto.getCustomerId() != null) {

            Customer customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> {
                        logger.warn("Customer not found with ID: {}", dto.getCustomerId());
                        return new RuntimeException("Customer not found");
                    });

            battery.setCustomer(customer);
        }

        return battery;
    }
    
   
    
}