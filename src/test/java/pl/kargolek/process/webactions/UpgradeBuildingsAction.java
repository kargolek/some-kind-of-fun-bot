package pl.kargolek.process.webactions;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;
import pl.kargolek.pages.game.details.ItemDetailsPageable;

import java.util.List;

/**
 * @author Karol Kuta-Orlowicz
 */

@Slf4j
public class UpgradeBuildingsAction extends WebActions {

    public UpgradeBuildingsAction(InitPages initPages) {
        super(initPages);
    }

    public void upgradeBuildingsProcess(ItemDetailsPageable itemDetailsPageable) {
        var pageableCLass = itemDetailsPageable.getClass().getSimpleName();
        log.info("Start upgrade building process for:{}", pageableCLass);
        tabSwitchToGame();
        itemDetailsPageable.open();
        if (!itemDetailsPageable.isUpgradeButtonNotAvailable()){
            var upgradeModalPage = itemDetailsPageable.clickUpgradeButton();
            if (upgradeModalPage.isOpenQueueButtonNotAvailable())
                upgradeModalPage.clickUpgradeButton();
        } else {
            log.info("Upgrade button not available for {}", pageableCLass);
        }
    }

    public List<ItemDetailsPageable> getItemUpgradePages() {
        return List.of(initPages.getGoldDetailViewPage(),
                initPages.getTreasuryDetailViewPage(),
                initPages.getAcademyDetailViewPage(),
                initPages.getTimberCampDetailViewPage(),
                initPages.getQuarryDetailViewPage(),
                initPages.getMarketDetailViewPage(),
                initPages.getTempleDetailViewPage(),
                initPages.getWallDetailViewPage());
    }
}
