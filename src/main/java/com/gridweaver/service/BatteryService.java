package com.gridweaver.service;

import com.gridweaver.dto.BatteryDTO;
import com.gridweaver.entity.Battery;
import com.gridweaver.exception.BatteryNotFoundException;
import com.gridweaver.repository.BatteryRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatteryService {

    @Autowired
    private BatteryRepository batteryRepository;

    @Autowired
    private ExecutorService virtualThreadExecutor;

    @Autowired
    private KafkaproducerService kafkaProducerService;

    @Autowired
    private BatteryWebSocketService batteryWebSocketService;

    // Save Battery
    public Battery saveBattery(Battery battery) {

        Battery savedBattery = batteryRepository.save(battery);

        kafkaProducerService.sendMessage(
                "Battery Added : " + savedBattery.getBatteryName());

        batteryWebSocketService.sendBatteryUpdate(savedBattery);

        return savedBattery;
    }

    // Get All Batteries
    public List<Battery> getAllBatteries() {
        return batteryRepository.findAll();
    }

    // Get Battery By Id
    public Battery getBatteryById(Long id) {

        return batteryRepository.findById(id)
                .orElseThrow(() ->
                        new BatteryNotFoundException(
                                "Battery not found with id: " + id));
    }

    // Update Battery
    public Battery updateBattery(Long id, Battery battery) {

        Battery existingBattery = batteryRepository.findById(id)
                .orElseThrow(() ->
                        new BatteryNotFoundException(
                                "Battery not found with id: " + id));

        existingBattery.setBatteryName(battery.getBatteryName());
        existingBattery.setLocation(battery.getLocation());
        existingBattery.setZone(battery.getZone());
        existingBattery.setPower(battery.getPower());
        existingBattery.setState(battery.getState());

        Battery updatedBattery = batteryRepository.save(existingBattery);

        kafkaProducerService.sendMessage(
                "Battery Updated : " + updatedBattery.getBatteryName());

        batteryWebSocketService.sendBatteryUpdate(updatedBattery);

        return updatedBattery;
    }

    // Delete Battery
    public void deleteBattery(Long id) {

        Battery battery = batteryRepository.findById(id)
                .orElseThrow(() ->
                        new BatteryNotFoundException(
                                "Battery not found with id: " + id));

        batteryRepository.delete(battery);

        kafkaProducerService.sendMessage(
                "Battery Deleted : " + battery.getBatteryName());

        batteryWebSocketService.sendMessage(
                "Battery Deleted Successfully");
    }

    // Get All Battery DTOs
    public List<BatteryDTO> getAllBatteryDTOs() {

        return batteryRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Convert Entity to DTO
    private BatteryDTO convertToDTO(Battery battery) {

        BatteryDTO dto = new BatteryDTO();

        dto.setId(battery.getId());
        dto.setBatteryName(battery.getBatteryName());
        dto.setLocation(battery.getLocation());
        dto.setZone(battery.getZone());
        dto.setPower(battery.getPower());
        dto.setState(battery.getState());

        return dto;
    }

    // Virtual Thread Demo
    public String processBatteryTask() throws Exception {

        Future<String> result = virtualThreadExecutor.submit(() -> {

            Thread.sleep(1000);

            return "Battery processed successfully using Virtual Thread: "
                    + Thread.currentThread();

        });

        return result.get();
    }

    // Virtual Thread Concurrency Test
    public String concurrencyTest() throws Exception {

        int totalTasks = 1000;

        for (int i = 1; i <= totalTasks; i++) {

            virtualThreadExecutor.submit(() -> {

                System.out.println("Running: " + Thread.currentThread());

                return null;
            });

        }

        return totalTasks + " Virtual Threads executed successfully.";
    }

}