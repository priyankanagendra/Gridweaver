package com.gridweaver.statemachine;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<BatteryState, BatteryEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<BatteryState, BatteryEvent> states)
            throws Exception {

        states.withStates()
                .initial(BatteryState.IDLE)
                .state(BatteryState.CHARGING)
                .state(BatteryState.DISCHARGING)
                .state(BatteryState.FAULT)
                .end(BatteryState.IDLE);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<BatteryState, BatteryEvent> transitions)
            throws Exception {

        transitions
                .withExternal()
                .source(BatteryState.IDLE)
                .target(BatteryState.CHARGING)
                .event(BatteryEvent.START_CHARGING)

                .and()

                .withExternal()
                .source(BatteryState.CHARGING)
                .target(BatteryState.DISCHARGING)
                .event(BatteryEvent.START_DISCHARGING)

                .and()

                .withExternal()
                .source(BatteryState.DISCHARGING)
                .target(BatteryState.IDLE)
                .event(BatteryEvent.STOP)

                .and()

                .withExternal()
                .source(BatteryState.CHARGING)
                .target(BatteryState.FAULT)
                .event(BatteryEvent.ERROR);
    }
}