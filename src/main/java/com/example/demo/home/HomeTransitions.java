package com.example.demo.home;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.manageStates.StateTransitions;
import io.github.jspinak.brobot.services.StateTransitionsRepository;
import org.springframework.stereotype.Component;

import static com.example.demo.world.World.Name.WORLD;
import static com.example.demo.home.Home.Name.HOME;

@Component
public class HomeTransitions {

    private final Action action;
    private final Home home;

    StateTransitions transitions;

    public HomeTransitions(StateTransitionsRepository stateTransitionsRepository,
                           Action action, Home home) {
        this.action = action;
        this.home = home;
        transitions = new StateTransitions.Builder(HOME)
                .addTransition(this::goToWorld, WORLD)
                .build();
        stateTransitionsRepository.add(transitions);
    }

    private boolean goToWorld() {
        return action.perform(ActionOptions.Action.CLICK, home.getToWorldButton()).isSuccess();
    }

}
