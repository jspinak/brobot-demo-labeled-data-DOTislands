package com.example.demo;

import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.database.state.ObjectCollection;
import com.example.demo.island.Island;
import com.example.demo.world.WorldTransitions;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GetNewIsland {

    private final Action action;
    private final Island island;
    private final WorldTransitions worldTransitions;

    private Map<String, String> islandTypes = new HashMap<>();
    {
        islandTypes.put("Burg", "Castle");
        islandTypes.put("Mine", "Mines");
        islandTypes.put("Farm", "Farms");
        islandTypes.put("Moun", "Mountains");
        islandTypes.put("Fore", "Forest");
        islandTypes.put("Lake", "Lakes");
    }

    public GetNewIsland(Action action, Island island, WorldTransitions worldTransitions) {
        this.action = action;
        this.island = island;
        this.worldTransitions = worldTransitions;
    }

    public String getIsland() {
        worldTransitions.goToIsland();
        String textRead = getIslandType();
        for (Map.Entry<String, String> type : islandTypes.entrySet()) {
            if (textRead.contains(type.getKey())) return type.getValue();
        }
        return "";
    }

    private String getIslandType() {
        ActionOptions getText = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.GET_TEXT)
                .getTextUntil(ActionOptions.GetTextUntil.TEXT_APPEARS)
                .setUseDefinedRegion(true)
                .setMaxWait(3)
                .setPauseBeforeBegin(3)
                .build();
        ObjectCollection islandName = new ObjectCollection.Builder()
                .withImages(island.getIslandName())
                .build();
        return action.perform(getText, islandName).getSelectedText();
    }
}
