package com.example.demo;

import com.example.demo.island.Island;
import io.github.jspinak.brobot.actions.actionExecution.Action;
import io.github.jspinak.brobot.actions.actionOptions.ActionOptions;
import io.github.jspinak.brobot.database.primitives.region.Region;
import io.github.jspinak.brobot.database.state.ObjectCollection;
import com.example.demo.world.World;
import org.springframework.stereotype.Component;

@Component
public class IslandRegion {

    private final Action action;
    private final World world;
    private final Island island;

    public IslandRegion(Action action, World world, Island island) {
        this.action = action;
        this.world = world;
        this.island = island;
    }

    public boolean defined() {
        if (island.getIslandRegion().defined()) return true;
        ActionOptions define = new ActionOptions.Builder()
                .setAction(ActionOptions.Action.DEFINE)
                .setDefineAs(ActionOptions.DefineAs.MATCH)
                .setAddX(-50)
                .setAddY(-250)
                .setAbsoluteWidth(200)
                .setAbsoluteHeight(200)
                .build();
        ObjectCollection searchButton = new ObjectCollection.Builder()
                .withImages(world.getSearchButton())
                .build();
        Region reg = action.perform(define, searchButton).getDefinedRegion();
        island.getIslandRegion().setSearchRegion(reg);
        return island.getIslandRegion().defined();
    }

    public Region getRegion() {
        return island.getIslandRegion().getSearchRegion();
    }
}
