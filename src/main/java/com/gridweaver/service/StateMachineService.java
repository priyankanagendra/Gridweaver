package com.gridweaver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import com.gridweaver.enums.BatteryEvent;
import com.gridweaver.enums.BatteryState;

@Service
public class StateMachineService {

    private static final Logger logger =
            LoggerFactory.getLogger(StateMachineService.class);

    private final StateMachineFactory<BatteryState, BatteryEvent> stateMachineFactory;

    public StateMachineService(
            StateMachineFactory<BatteryState, BatteryEvent> stateMachineFactory) {

        this.stateMachineFactory = stateMachineFactory;
    }

    public void printCurrentState() {

        StateMachine<BatteryState, BatteryEvent> stateMachine =
                stateMachineFactory.getStateMachine();

        stateMachine.start();

        logger.info("Current State: {}", stateMachine.getState().getId());
    }
}