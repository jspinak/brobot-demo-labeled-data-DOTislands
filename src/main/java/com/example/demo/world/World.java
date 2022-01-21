package com.example.demo.world;

import io.github.jspinak.brobot.database.state.state.State;
import io.github.jspinak.brobot.database.state.stateObject.stateImageObject.StateImageObject;
import io.github.jspinak.brobot.primatives.enums.StateEnum;
import io.github.jspinak.brobot.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class World {

    public enum Name implements StateEnum {
        WORLD
    }

    private StateImageObject searchButton = new StateImageObject.Builder()
            .withImage("searchButton")
            .isFixed()
            .build();

    private State state = new State.Builder(Name.WORLD)
            .withImages(searchButton)
            .build();

    public World(StateService stateService) { stateService.save(state); }
}
