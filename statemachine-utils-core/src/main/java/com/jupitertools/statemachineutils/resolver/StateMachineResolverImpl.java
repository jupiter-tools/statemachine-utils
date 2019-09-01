package com.jupitertools.statemachineutils.resolver;


import java.util.List;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.support.DefaultStateContext;
import org.springframework.statemachine.transition.Transition;
import org.springframework.statemachine.trigger.Trigger;

import static java.util.stream.Collectors.toList;

/**
 * Created on 02.06.2018.
 *
 * @author Korovin Anatoliy
 */
public class StateMachineResolverImpl<StatesT, EventsT> implements StateMachineResolver<StatesT, EventsT> {

    @Override
    public List<EventsT> getAvailableEvents(StateMachine<StatesT, EventsT> stateMachine) {

        return stateMachine.getTransitions()
                           .stream()
                           .filter(t -> isTransitionSourceFromCurrentState(t, stateMachine))
                           .filter(t -> evaluateGuardCondition(stateMachine, t))
                           .map(Transition::getTrigger)
                           .map(Trigger::getEvent)
                           .collect(toList());
    }


    private boolean isTransitionSourceFromCurrentState(Transition<StatesT, EventsT> transition,
                                                       StateMachine<StatesT, EventsT> stateMachine) {

        return stateMachine.getState().getId() == transition.getSource().getId();
    }


    private boolean evaluateGuardCondition(StateMachine<StatesT, EventsT> stateMachine,
                                           Transition<StatesT, EventsT> transition) {

        if (transition.getGuard() == null) {
            return true;
        }

        StateContext<StatesT, EventsT> context = makeStateContext(stateMachine, transition);

        try {
            return transition.getGuard().evaluate(context);
        } catch (Exception e) {
            return false;
        }
    }


    private DefaultStateContext<StatesT, EventsT> makeStateContext(StateMachine<StatesT, EventsT> stateMachine,
                                                                   Transition<StatesT, EventsT> transition) {

        return new DefaultStateContext<>(StateContext.Stage.TRANSITION,
                                         null,
                                         null,
                                         stateMachine.getExtendedState(),
                                         transition,
                                         stateMachine,
                                         stateMachine.getState(),
                                         transition.getTarget(),
                                         null);
    }
}
