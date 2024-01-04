package pl.kargolek.process;

import pl.kargolek.process.webactions.BuildingLevelActions;

/**
 * @author Karol Kuta-Orlowicz
 */
public class BuildingLevelService {

    private final BuildingLevelActions buildingLevelActions;

    public BuildingLevelService(BuildingLevelActions buildingLevelActions) {
        this.buildingLevelActions = buildingLevelActions;
    }

    public String getBuildingsLevels(){
        buildingLevelActions.getBuildingsPages()
                .forEach(buildingLevelActions::getItemLevel);
        return buildingLevelActions.getBuildingsLevelsList();
    }
}
