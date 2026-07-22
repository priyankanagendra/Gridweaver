package com.gridweaver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import com.gridweaver.entity.Battery;
import com.gridweaver.enums.BatteryEvent;
import com.gridweaver.enums.BatteryState;
import com.gridweaver.repository.BatteryRepository;

@Service
public class StateMachineService {

    private static final Logger logger =
            LoggerFactory.getLogger(StateMachineService.class);

    private final StateMachineFactory<BatteryState, BatteryEvent> stateMachineFactory;
    private final BatteryRepository batteryRepository;

    public StateMachineService(
            StateMachineFactory<BatteryState, BatteryEvent> stateMachineFactory,
            BatteryRepository batteryRepository) {

        this.stateMachineFactory = stateMachineFactory;
        this.batteryRepository = batteryRepository;
    }

    public void printCurrentState(Long batteryId) {

        Battery battery = batteryRepository.findById(batteryId)
                .orElseThrow(() -> new RuntimeException(
                        "Battery not found with ID: " + batteryId));

        StateMachine<BatteryState, BatteryEvent> stateMachine =
                stateMachineFactory.getStateMachine();

        stateMachine.start();

        logger.info("Current State : {}", stateMachine.getState().getId());

        stateMachine.sendEvent(
                MessageBuilder.withPayload(BatteryEvent.START_CHARGING).build());

        logger.info("New State : {}", stateMachine.getState().getId());

        // Update Battery entity with the new state
        battery.setState(stateMachine.getState().getId());

        // Save updated Battery
        batteryRepository.save(battery);

        logger.info("Battery {} state saved to database: {}",
                battery.getId(),
                battery.getState());
    }
}