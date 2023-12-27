package pl.kargolek.process;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kargolek.process.webactions.BuildingQueueActions;
import pl.kargolek.process.webactions.UpgradeBuildingsAction;

/**
 * @author Karol Kuta-Orlowicz
 */
@Slf4j
@AllArgsConstructor
public class UpgradeBuildingsService {

    private BuildingQueueActions buildingQueueActions;
    private UpgradeBuildingsAction upgradeBuildingsAction;

    public void runUpgradeProcess(boolean exceedMinResourceReq) throws InterruptedException {
        if (exceedMinResourceReq) {
            var buildingQueueSize = buildingQueueActions.getQueueNumber();
            if (buildingQueueSize.equals(0)) {
                log.info("Start upgrade process");
                upgradeBuildingsAction.getItemUpgradePages()
                        .forEach(upgradeBuildingsAction::upgradeBuildingsProcess);
            }
        } else {
            log.info("Upgrade process has been skipped");
        }
    }

    public void logBuildingQueueList() throws InterruptedException {
        var buildingQueueText = buildingQueueActions.getUpgradeItemsListText();
        log.info("Building queue: {}", buildingQueueText);
    }
}
