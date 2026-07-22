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

	        .withExternal()
	            .source(BatteryState.IDLE)
	            .target(BatteryState.CHARGING)
	            .event(BatteryEvent.START_CHARGING)

	        .and()

	        .withExternal()
	            .source(BatteryState.CHARGING)
	            .target(BatteryState.IDLE)
	            .event(BatteryEvent.STOP_CHARGING);

	}
}