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

	@Test
	void writeMultipleAndReadSingle() throws Exception {
		// Arrange
		InMemoryStateMachinePersist persist = new InMemoryStateMachinePersist();
		UUID firstId = UUID.randomUUID();
		UUID secondId = UUID.randomUUID();
		StateMachineContext firstContext = mock(StateMachineContext.class);
		StateMachineContext secondContext = mock(StateMachineContext.class);
		// Act
		persist.write(firstContext, firstId);
		persist.write(secondContext, secondId);
		// Assert
		StateMachineContext first = persist.read(firstId);
		StateMachineContext second = persist.read(secondId);
		assertThat(first).isEqualTo(firstContext);
		assertThat(second).isEqualTo(secondContext);
	}

	@Test
	void readNotExisted() throws Exception {
		// Arrange
		InMemoryStateMachinePersist persist = new InMemoryStateMachinePersist();
		// Act
		StateMachineContext first = persist.read(UUID.randomUUID());
		// Assert
		assertThat(first).isNull();
	}

	@Test
	void writeAndDelete() throws Exception {
		// Arrange
		InMemoryStateMachinePersist persist = new InMemoryStateMachinePersist();
		UUID id = UUID.randomUUID();
		StateMachineContext context = mock(StateMachineContext.class);
		// Act
		persist.write(context, id);
		persist.remove(id);
		// Assert
		assertThat(persist.read(id)).isNull();
	}
}