package com.gridweaver.statemachine;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;

import com.gridweaver.enums.BatteryEvent;
import com.gridweaver.enums.BatteryState;

import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import java.util.EnumSet;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
@Configuration
@EnableStateMachineFactory
public class StateMachineConfig
        extends EnumStateMachineConfigurerAdapter<BatteryState, BatteryEvent> {

	@Override
	public void configure(StateMachineStateConfigurer<BatteryState, BatteryEvent> states)
	        throws Exception {

	    states
	        .withStates()
	        .initial(BatteryState.IDLE)
	        .states(EnumSet.allOf(BatteryState.class));

	}
	
	@Override
	public void configure(
	        StateMachineTransitionConfigurer<BatteryState, BatteryEvent> transitions)
	        throws Exception {

	    transitions

	        // IDLE -> CHARGING
	        .withExternal()
	            .source(BatteryState.IDLE)
	            .target(BatteryState.CHARGING)
	            .event(BatteryEvent.START_CHARGING)

	        .and()

	        // CHARGING -> IDLE
	        .withExternal()
	            .source(BatteryState.CHARGING)
	            .target(BatteryState.IDLE)
	            .event(BatteryEvent.STOP_CHARGING)

	        .and()

	        // IDLE -> DISCHARGING
	        .withExternal()
	            .source(BatteryState.IDLE)
	            .target(BatteryState.DISCHARGING)
	            .event(BatteryEvent.START_DISCHARGING)

	        .and()

	        // DISCHARGING -> IDLE
	        .withExternal()
	            .source(BatteryState.DISCHARGING)
	            .target(BatteryState.IDLE)
	            .event(BatteryEvent.STOP_DISCHARGING)

	        .and()

	        // Any State -> FAULT
	        .withExternal()
	            .source(BatteryState.IDLE)
	            .target(BatteryState.FAULT)
	            .event(BatteryEvent.DETECT_FAULT)

	        .and()

	        .withExternal()
	            .source(BatteryState.CHARGING)
	            .target(BatteryState.FAULT)
	            .event(BatteryEvent.DETECT_FAULT)

	        .and()

	        .withExternal()
	            .source(BatteryState.DISCHARGING)
	            .target(BatteryState.FAULT)
	            .event(BatteryEvent.DETECT_FAULT)

	        .and()

	        // FAULT -> IDLE
	        .withExternal()
	            .source(BatteryState.FAULT)
	            .target(BatteryState.IDLE)
	            .event(BatteryEvent.RESET);
	}
}