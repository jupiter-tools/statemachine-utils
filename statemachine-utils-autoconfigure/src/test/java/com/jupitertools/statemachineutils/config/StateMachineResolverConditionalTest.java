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

/**
 * The test of the {@link org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean}
 * which uses in the auto-configuration file of the StateMachineResolver.
 *
 * @author Anatoliy Korovin
 */
@SpringBootTest
class StateMachineResolverConditionalTest {

	@Autowired
	private StateMachineResolver<States, Events> resolver;

	/**
	 * In this test, we expect an injecting of the resolver from the nested configuration,
	 * instead of the resolver from the auto-configuration file.
	 */
	@Test
	void customResolverNotReplaced() {
		assertThat(resolver).isNotInstanceOf(StateMachineResolverImpl.class);
	}

	/**
	 * Nested test configuration where we declare own {@link StateMachineResolver}
	 * instead of the auto-created instance from the {@link StateMachineResolverAutoConfiguration} file.
	 */
	@TestConfiguration
	public static class TestConfig {

		@Bean
		public StateMachineResolver<States, Events> customResolver() {
			return stateMachine -> null;
		}
	}
}
