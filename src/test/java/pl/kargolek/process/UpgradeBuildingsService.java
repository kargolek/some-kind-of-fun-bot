package pl.kargolek.process;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kargolek.process.webactions.BuildingQueueActions;
import pl.kargolek.process.webactions.UpgradeBuildingsAction;

import java.util.stream.Collectors;

/**
 * @author Karol Kuta-Orlowicz
 */
@Slf4j
@AllArgsConstructor
public class UpgradeBuildingsService {

    private BuildingQueueActions buildingQueueActions;
    private UpgradeBuildingsAction upgradeBuildingsAction;

    public String runUpgradeProcess(boolean exceedMinResourceReq) throws InterruptedException {
        if (exceedMinResourceReq) {
            var buildingQueueSize = buildingQueueActions.getQueueNumber();
            if (buildingQueueSize.equals(0)) {
                log.info("Start upgrade process");

                return upgradeBuildingsAction.getItemUpgradePages()
                        .stream()
                        .map(upgradeBuildingsAction::upgradeBuildingsProcess)
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
}
