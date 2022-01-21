package com.example.demo.world;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.database.state.ObjectCollection;
import io.github.jspinak.brobot.manageStates.StateTransition;
import io.github.jspinak.brobot.manageStates.StateTransitions;
import io.github.jspinak.brobot.services.StateTransitionsRepository;
import org.springframework.stereotype.Component;

import static com.example.demo.island.Island.Name.ISLAND;
import static com.example.demo.world.World.Name.WORLD;
import static io.github.jspinak.brobot.manageStates.StateTransition.StaysVisible.TRUE;

@Component
public class WorldTransitions {

    private final Action action;
    private final World world;

    StateTransitions transitions;

    public WorldTransitions(StateTransitionsRepository stateTransitionsRepository,
                            Action action, World world) {
        this.action = action;
        this.world = world;
        transitions = new StateTransitions.Builder(WORLD)
                .addTransitionFinish(this::finishTransition)
                .addTransition(new StateTransition.Builder()
                        .addToActivate(ISLAND)
                        .setFunction(this::goToIsland)
                        .setStaysVisibleAfterTransition(TRUE)
                        .build())
                .build();
        stateTransitionsRepository.add(transitions);
    }

    private boolean finishTransition() {
        ActionOptions find = new ActionOptions.Builder()
                .setMaxWait(10)
                .build();
        ObjectCollection worldImages = new ObjectCollection.Builder()
                .withAllStateImages(world.getState())
                .build();
        return action.perform(find, worldImages).isSuccess();
    }

    public boolean goToIsland() {
        ActionOptions clickTwice = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.CLICK)
                .setTimesToRepeatIndividualAction(2)
                .setPauseBetweenActions(.2)
                .build();
        ObjectCollection searchButton = new ObjectCollection.Builder()
                .withImages(world.getSearchButton())
                .build();
        return action.perform(clickTwice, searchButton).isSuccess();
    }

}
