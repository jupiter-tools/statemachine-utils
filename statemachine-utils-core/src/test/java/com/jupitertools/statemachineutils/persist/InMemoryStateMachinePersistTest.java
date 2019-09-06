package com.jupitertools.statemachineutils.persist;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import org.springframework.statemachine.StateMachineContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class InMemoryStateMachinePersistTest {

	@Test
	void simpleWriteReadTest() throws Exception {
		// Arrange
		InMemoryStateMachinePersist persist = new InMemoryStateMachinePersist();
		UUID id = UUID.randomUUID();
		StateMachineContext context = mock(StateMachineContext.class);
		// Act
		persist.write(context, id);
		// Assert
		StateMachineContext readContext = persist.read(id);
		assertThat(context).isEqualTo(readContext);
	}
}