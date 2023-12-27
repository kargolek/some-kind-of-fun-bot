package pl.kargolek.process.webactions;

import pl.kargolek.pages.InitPages;

/**
 * @author Karol Kuta-Orlowicz
 */
public class BuildingQueueActions extends WebActions{

    public BuildingQueueActions(InitPages initPages) {
        super(initPages);
    }

    public Integer getQueueNumber() throws InterruptedException {
        return this.initPages.getGameMainPage()
                .open()
                .getSideNavigationPage()
                .clickBuildingQueueButton()
                .getNumberOfUpgradedItems();
    }

    public String getUpgradeItemsListText() throws InterruptedException {
        return this.initPages.getGameMainPage()
                .open()
                .getSideNavigationPage()
                .clickBuildingQueueButton()
                .getItemsQueueContainerText();
    }
}
