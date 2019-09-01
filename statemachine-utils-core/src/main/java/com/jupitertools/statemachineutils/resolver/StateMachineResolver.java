package com.jupitertools.statemachineutils.resolver;

import java.util.List;

import org.springframework.statemachine.StateMachine;

/**
 * Created on 02.06.2018.
 *
 * @author Korovin Anatoliy
 */
public interface StateMachineResolver<S, E> {

    /**
     * Evaluate available events from a current state of the state-machine
     *
     * @param stateMachine state machine
     *
     * @return available events collection
     */
    List<E> getAvailableEvents(StateMachine<S, E> stateMachine);
}
