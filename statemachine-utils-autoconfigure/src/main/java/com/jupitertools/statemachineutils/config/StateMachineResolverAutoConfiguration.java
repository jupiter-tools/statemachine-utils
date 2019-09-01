package com.jupitertools.statemachineutils.config;


import com.jupitertools.statemachineutils.resolver.StateMachineResolver;
import com.jupitertools.statemachineutils.resolver.StateMachineResolverImpl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnMissingBean(StateMachineResolver.class)
public class StateMachineResolverAutoConfiguration {

	@Bean
	public <S,E> StateMachineResolver<S,E> stateMachineResolver(){
		return new StateMachineResolverImpl<>();
	}
}
