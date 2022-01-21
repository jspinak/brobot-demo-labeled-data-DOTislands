package com.example.demo;

import io.github.jspinak.brobot.imageUtils.ImageUtils;
import io.github.jspinak.brobot.manageStates.StateTransitionsManagement;
import io.github.jspinak.brobot.reports.Report;
import org.springframework.stereotype.Component;

import static com.example.demo.island.Island.Name.ISLAND;

@Component
public class SaveLabeledImages {

    private StateTransitionsManagement stateTransitionsManagement;
    private ImageUtils imageUtils;
    private GetNewIsland getNewIsland;
    private IslandRegion islandRegion;

    public SaveLabeledImages(StateTransitionsManagement stateTransitionsManagement,
                             ImageUtils imageUtils, GetNewIsland getNewIsland,
                             IslandRegion islandRegion) {
        this.stateTransitionsManagement = stateTransitionsManagement;
        this.imageUtils = imageUtils;
        this.getNewIsland = getNewIsland;
        this.islandRegion = islandRegion;
    }

    public void saveImages(int maxImages) {
        String directory = "labeledImages/";
        if (!stateTransitionsManagement.openState(ISLAND)) return;
        for (int i=0; i<maxImages; i++) {
            String newIslandType = getNewIsland.getIsland();
            Report.println("text = "+newIslandType);
            if (!newIslandType.isEmpty() && islandRegion.defined()) {
                imageUtils.saveRegionToFile(islandRegion.getRegion(), directory + newIslandType);
            }
            Report.println();
        }
    }
}
