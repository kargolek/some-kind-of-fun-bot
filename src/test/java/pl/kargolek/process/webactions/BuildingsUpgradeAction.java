package pl.kargolek.process.webactions;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;
import pl.kargolek.pages.game.details.BuildingDetailsPageable;
import pl.kargolek.pages.game.modal.UpgradeModalPage;

import java.util.List;

/**
 * @author Karol Kuta-Orlowicz
 */

@Slf4j
public class BuildingsUpgradeAction extends WebActions {

    public BuildingsUpgradeAction(InitPages initPages) {
        super(initPages);
    }

    public String upgradeBuildingsProcess(BuildingDetailsPageable buildingDetailsPageable) {
        tabSwitchToGame();
        buildingDetailsPageable.open();
        if (!buildingDetailsPageable.isUpgradeButtonNotAvailable()){
            var upgradeModalPage = buildingDetailsPageable.clickUpgradeButton();
            if (upgradeModalPage.isOpenQueueButtonNotAvailable()){
                return startUpgradeInModalPage(upgradeModalPage, buildingDetailsPageable);
            }
        }
        return "";
    }

    public List<BuildingDetailsPageable> getItemUpgradePages() {
        return List.of(initPages.getGoldDetailViewPage(),
                initPages.getTreasuryDetailViewPage(),
                initPages.getAcademyDetailViewPage(),
                initPages.getTimberCampDetailViewPage(),
                initPages.getQuarryDetailViewPage(),
                initPages.getMarketDetailViewPage(),
                initPages.getTempleDetailViewPage(),
                initPages.getWallDetailViewPage(),
                initPages.getYourCampDetailPage());
    }

    private String startUpgradeInModalPage(UpgradeModalPage upgradeModalPage, BuildingDetailsPageable buildingDetailsPageable) {
        if (upgradeModalPage.isUpgradeButtonAvailable()) {
            upgradeModalPage.clickUpgradeButton();
            log.info("Item: {} upgrade process has been started",
                    buildingDetailsPageable.getClass().getSimpleName());
            return buildingDetailsPageable.getClass().getSimpleName();
        }
        return "";
    }
}
