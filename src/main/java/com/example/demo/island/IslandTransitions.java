package com.example.demo.island;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.database.state.ObjectCollection;
import io.github.jspinak.brobot.manageStates.StateTransitions;
import io.github.jspinak.brobot.services.StateTransitionsRepository;
import org.springframework.stereotype.Component;

import static com.example.demo.island.Island.Name.ISLAND;
import static io.github.jspinak.brobot.actions.actionOptions.ActionOptions.Action.FIND;

@Component
public class IslandTransitions {

    private final Action action;
    private final Island island;

    StateTransitions transitions;

    public IslandTransitions(StateTransitionsRepository stateTransitionsRepository,
                             Action action, Island island) {
        this.action = action;
        this.island = island;
        transitions = new StateTransitions.Builder(ISLAND)
                .addTransitionFinish(this::finishTransition)
                .build();
        stateTransitionsRepository.add(transitions);
    }

    private boolean finishTransition() {
        ObjectCollection worldImages = new ObjectCollection.Builder()
                .withAllStateImages(island.getState())
                .build();
        return action.perform(FIND, worldImages).isSuccess();
    }

}
