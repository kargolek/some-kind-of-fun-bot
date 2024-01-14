package pl.kargolek.process;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.game.details.BuildingDetailsPageable;
import pl.kargolek.process.data.BuildingLevel;
import pl.kargolek.process.enums.Building;
import pl.kargolek.process.webactions.BuildingQueueActions;
import pl.kargolek.process.webactions.BuildingsUpgradeAction;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Karol Kuta-Orlowicz
 */
@Slf4j
@AllArgsConstructor
public class BuildingsUpgradeService {

    private BuildingQueueActions buildingQueueActions;
    private BuildingsUpgradeAction buildingsUpgradeAction;

    public String runUpgradeProcess(boolean exceedMinResourceReq,
                                    List<BuildingLevel> currentBuildingLevels,
                                    List<BuildingLevel> maxBuildingLevels) throws InterruptedException {
        if (exceedMinResourceReq) {
            var buildingQueueSize = buildingQueueActions.getQueueNumber();
            if (buildingQueueSize.equals(0)) {
                log.info("Start upgrade process");

                return getFilteredBuildingPageable(currentBuildingLevels, maxBuildingLevels)
                        .stream()
                        .map(buildingsUpgradeAction::upgradeBuildingsProcess)
                        .filter(e -> !e.equals(""))
                        .collect(Collectors.joining(" "));
            }
        } else {
            log.info("Upgrade process has been skipped");
        }
        return "Upgrade process has been skipped";
    }

    public String getBuildingQueueList() throws InterruptedException {
        var buildingQueueText = buildingQueueActions.getUpgradeItemsListText();
        log.info("Building queue: {}", buildingQueueText);
        return buildingQueueText;
    }

    private List<BuildingDetailsPageable> getFilteredBuildingPageable(List<BuildingLevel> currentBuildingLevels,
                                                                      List<BuildingLevel> maxBuildingLevels) {
        var senateLevel = getBuildingLevel(currentBuildingLevels, Building.SENATE);
        var filteredBuildingPageable = buildingsUpgradeAction.getItemUpgradePages()
                .stream()
                .filter(buildingDetailsPageable -> isCurrentLevelBelowMaxAboveSenateLevel(
                        getBuildingLevel(currentBuildingLevels, buildingDetailsPageable.getBuildingType()),
                        senateLevel,
                        getBuildingLevel(maxBuildingLevels, buildingDetailsPageable.getBuildingType())
                )).collect(Collectors.toList());
        log.info("Filtered list: {}", filteredBuildingPageable);
        return filteredBuildingPageable;
    }

    private int getBuildingLevel(List<BuildingLevel> buildingLevels, Building building) {
        return buildingLevels.stream()
                .filter(buildingLevel -> buildingLevel.building() == building)
                .findFirst()
                .orElseThrow()
                .level();
    }

    private boolean isCurrentLevelBelowMaxAboveSenateLevel(int currentLevel,
                                                           int senateLevel,
                                                           int maxLevel) {
        log.info("Current level:{}, Senate Level:{}, Max Level: {}", currentLevel, senateLevel, maxLevel);
        return (currentLevel - senateLevel) < maxLevel;
    }
}
