package com.jupitertools.statemachineutils.persist;

import java.util.UUID;

import com.jupitertools.statemachineutils.testconfig.Events;
import com.jupitertools.statemachineutils.testconfig.States;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import static com.jupitertools.statemachineutils.testconfig.States.BACKLOG;
import static com.jupitertools.statemachineutils.testconfig.States.IN_PROGRESS;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class DefaultStateMachinePersisterDecoratorTest {

	@Autowired
	private StateMachineFactory<States, Events> factory;

	@Test
	void name() throws Exception {

		InMemoryStateMachinePersist inMemoryStateMachinePersist = new InMemoryStateMachinePersist();
		DefaultStateMachinePersisterDecorator<States, Events, UUID> persisterDecorator =
				new DefaultStateMachinePersisterDecorator<States, Events, UUID>(inMemoryStateMachinePersist);

		StateMachine<States, Events> machine = factory.getStateMachine();
		machine.sendEvent(Events.START_FEATURE);
		assertThat(machine.getState().getId()).isEqualTo(IN_PROGRESS);

		StateMachine<States, Events> tmpMachine = factory.getStateMachine();
		assertThat(tmpMachine.getState().getId()).isEqualTo(BACKLOG);

		// Act
		persisterDecorator.persist(machine, machine.getUuid());

		// Assert
		persisterDecorator.restore(tmpMachine, machine.getUuid());
		assertThat(tmpMachine.getState().getId()).isEqualTo(IN_PROGRESS);
	}
}