package com.gridweaver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gridweaver.dto.BalanceRequest;
import com.gridweaver.entity.Battery;
import com.gridweaver.exception.BatteryNotFoundException;
import com.gridweaver.repository.BatteryRepository;

@Service
public class RegionalBalancingService {

    @Autowired
    private BatteryRepository batteryRepository;

    @Autowired
    private KafkaproducerService kafkaProducerService;

    public String balancePower(BalanceRequest request) {

        Battery sourceBattery = batteryRepository.findById(request.getSourceBatteryId())
                .orElseThrow(() ->
                        new BatteryNotFoundException("Source Battery Not Found"));

        Battery targetBattery = batteryRepository.findById(request.getTargetBatteryId())
                .orElseThrow(() ->
                        new BatteryNotFoundException("Target Battery Not Found"));

        double transferPower = request.getTransferPower();

        if (sourceBattery.getPower() < transferPower) {
            return "Insufficient Power in Source Battery";
        }

        sourceBattery.setPower(sourceBattery.getPower() - transferPower);
        targetBattery.setPower(targetBattery.getPower() + transferPower);

        batteryRepository.save(sourceBattery);
        batteryRepository.save(targetBattery);

        kafkaProducerService.sendMessage(
                "Power Transferred from "
                        + sourceBattery.getBatteryName()
                        + " to "
                        + targetBattery.getBatteryName()
                        + " : "
                        + transferPower + " kW");

        return "Power transferred successfully";
    }
}
