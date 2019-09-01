package com.jupitertools.statemachineutils.resolver;


import java.util.List;

import com.jupitertools.statemachineutils.testconfig.Events;
import com.jupitertools.statemachineutils.testconfig.StateMachineConfig;
import com.jupitertools.statemachineutils.testconfig.States;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Anatoliy Korovin
 */
@SpringBootTest
@Import(StateMachineConfig.class)
class StateMachineResolverTest {

	@Autowired
	private StateMachineResolver<States, Events> resolver;

	@Autowired
	private StateMachineFactory<States, Events> factory;

	@Test
	void resolverExistInApplicationContext() {
		assertThat(resolver).isNotNull();
	}

	@Test
	void testResolverWithoutGuard() {
		// Arrange
		StateMachine<States, Events> machine = factory.getStateMachine();
		// Act
		List<Events> availableEvents = resolver.getAvailableEvents(machine);
		// Asserts
		assertThat(availableEvents).containsOnly(Events.START_FEATURE,
		                                         Events.ROCK_STAR_DOUBLE_TASK,
		                                         Events.DEPLOY,
		                                         Events.INCREMENT);
	}

	@Test
	void testResolverWithGuard() {
		// Arrange
		StateMachine<States, Events> machine = factory.getStateMachine();
		machine.sendEvent(Events.START_FEATURE);
		// Act
		List<Events> availableEvents = resolver.getAvailableEvents(machine);
		// Asserts
		assertThat(availableEvents).containsOnly(Events.DEPLOY,
		                                         Events.INCREMENT);
	}

	@TestConfiguration
	public static class TestConfig {

		@Bean
		public StateMachineResolver<States, Events> resolver() {
			return new StateMachineResolverImpl<>();
		}
	}
}