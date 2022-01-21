package com.example.demo.island;

import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.database.primitives.match.MatchSnapshot;
import io.github.jspinak.brobot.database.state.state.State;
import io.github.jspinak.brobot.database.state.stateObject.otherStateObjects.StateRegion;
import io.github.jspinak.brobot.database.state.stateObject.stateImageObject.StateImageObject;
import io.github.jspinak.brobot.primatives.enums.StateEnum;
import io.github.jspinak.brobot.services.StateService;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Island {

    public enum Name implements StateEnum {
        ISLAND
    }

    private StateImageObject islandName = new StateImageObject.Builder()
            .withImage("castle", "mines", "farms", "forest", "mountains", "lakes")
            .called("island type text")
            .isFixed()
            .addSnapshot(new MatchSnapshot.Builder()
                    .setActionOptions(ActionOptions.Action.GET_TEXT)
                    .addString("Mines")
                    .addString("Lakess")
                    .addString("Farmz")
                    .build())
            .build();
    private StateRegion islandRegion = new StateRegion.Builder()
            .called("island image region")
            .build();

    private State state = new State.Builder(Name.ISLAND)
            .withImages(islandName)
            .withRegions(islandRegion)
            .build();

    private Island(StateService stateService) { stateService.save(state); }
}
