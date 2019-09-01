package com.jupitertools.statemachineutils.config;

import com.jupitertools.statemachineutils.resolver.StateMachineResolver;
import com.jupitertools.statemachineutils.resolver.StateMachineResolverImpl;
import com.jupitertools.statemachineutils.testconfig.Events;
import com.jupitertools.statemachineutils.testconfig.States;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StateMachineResolverAutoConfigurationTest {

	@Autowired
	private StateMachineResolver<States, Events> resolver;

	@Test
	void resolverExistInApplicationContext() {
		assertThat(resolver).isNotNull();
	}

	@Test
	void testImplementation() {
		assertThat(resolver).isInstanceOf(StateMachineResolverImpl.class);
	}
}