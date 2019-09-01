package com.jupitertools.statemachineutils.config;

import com.jupitertools.statemachineutils.resolver.StateMachineResolver;
import com.jupitertools.statemachineutils.resolver.StateMachineResolverImpl;
import com.jupitertools.statemachineutils.testconfig.Events;
import com.jupitertools.statemachineutils.testconfig.States;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ConditionalTest {

	@Autowired
	private StateMachineResolver<States, Events> resolver;

	@Test
	void customResolverNotReplaced() {
		assertThat(resolver).isNotInstanceOf(StateMachineResolverImpl.class);
	}

	@TestConfiguration
	public static class TestConfig {

		@Bean
		public StateMachineResolver<States, Events> customResolver() {
			return stateMachine -> null;
		}
	}
}
