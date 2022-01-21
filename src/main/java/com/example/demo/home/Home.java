package com.example.demo.home;

import io.github.jspinak.brobot.database.primitives.match.MatchSnapshot;
import io.github.jspinak.brobot.database.state.state.State;
import io.github.jspinak.brobot.database.state.stateObject.stateImageObject.StateImageObject;
import io.github.jspinak.brobot.primatives.enums.StateEnum;
import io.github.jspinak.brobot.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

import static com.example.demo.home.Home.Name.HOME;

@Component
@Getter
public class Home {

    public enum Name implements StateEnum {
        HOME
    }

    private StateImageObject toWorldButton = new StateImageObject.Builder()
            .withImage("toWorldButton")
            .isFixed()
            .addSnapshot(new MatchSnapshot(220, 600, 20, 20))
            .build();

    private State state = new State.Builder(HOME)
            .withImages(toWorldButton)
            .build();

    private Home(StateService stateService) { stateService.save(state); }
}
