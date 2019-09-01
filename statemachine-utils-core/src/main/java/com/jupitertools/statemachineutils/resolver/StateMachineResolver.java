package com.jupitertools.statemachineutils.resolver;

import java.util.List;

import org.springframework.statemachine.StateMachine;

/**
 * Created on 02.06.2018.
 *
 * Provides an ability to resolve all available transitions
 * from the current state of the state machine, based on
 * existed transitions and also checks the guard condition on
 * each transitions while resolve.
 *
 * Return the list of available events from the current state of the state machine.
 *
 * @author Anatoliy Korovin
 */
public interface StateMachineResolver<S, E> {

    /**
     * Evaluate available events from a current state of the state-machine.
     * Resolve all available transitions from the current state, based on
     * existed transitions and also checks the guard condition on each
     * transitions.
     *
     * @param stateMachine state machine
     *
     * @return available events collection
     */
    List<E> getAvailableEvents(StateMachine<S, E> stateMachine);
}
