package pl.kargolek.process;

import pl.kargolek.process.data.BuildingLevel;
import pl.kargolek.process.webactions.BuildingLevelActions;

import java.util.List;

/**
 * @author Karol Kuta-Orlowicz
 */
public class BuildingLevelService {

    private final BuildingLevelActions buildingLevelActions;

    public BuildingLevelService(BuildingLevelActions buildingLevelActions) {
        this.buildingLevelActions = buildingLevelActions;
    }

    public List<BuildingLevel> getBuildingsLevels(){
        buildingLevelActions.getBuildingsPages()
                .forEach(buildingLevelActions::getItemLevel);
        return buildingLevelActions.getBuildingsLevelsList();
    }
}
