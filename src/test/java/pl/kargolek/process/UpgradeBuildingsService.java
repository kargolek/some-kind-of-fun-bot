package pl.kargolek.process;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;
import pl.kargolek.pages.game.details.ItemDetailsPageable;
import pl.kargolek.process.webactions.BuildingQueueActions;
import pl.kargolek.process.webactions.UpgradeBuildingsAction;

import java.util.List;

/**
 * @author Karol Kuta-Orlowicz
 */
@Slf4j
@AllArgsConstructor
public class UpgradeBuildingsService {

    private BuildingQueueActions buildingQueueActions;
    private UpgradeBuildingsAction upgradeBuildingsAction;

    public void runUpgradeProcess() throws InterruptedException {
        var buildingQueueSize = buildingQueueActions.getQueueNumber();
        if (buildingQueueSize.equals(0)){
            log.info("Start upgrade process");
            upgradeBuildingsAction.getItemUpgradePages()
                    .forEach(upgradeBuildingsAction::upgradeBuildingsProcess);
        }
    }

}
