package com.gridweaver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import com.gridweaver.statemachine.BatteryEvent;
import com.gridweaver.statemachine.BatteryState;

@Service
public class StateMachineService {

    @Autowired
    private StateMachine<BatteryState, BatteryEvent> stateMachine;

    public String startCharging() {

        stateMachine.start();

        stateMachine.sendEvent(BatteryEvent.START_CHARGING);

        return "Current State : " + stateMachine.getState().getId();
    }

    public String startDischarging() {

        stateMachine.start();

        stateMachine.sendEvent(BatteryEvent.START_DISCHARGING);

        return "Current State : " + stateMachine.getState().getId();
    }

    public String stopBattery() {

        stateMachine.start();

        stateMachine.sendEvent(BatteryEvent.STOP);

        return "Current State : " + stateMachine.getState().getId();
    }

    public String batteryError() {

        stateMachine.start();

        stateMachine.sendEvent(BatteryEvent.ERROR);

        return "Current State : " + stateMachine.getState().getId();
    }
}