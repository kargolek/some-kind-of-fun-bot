package pl.kargolek.pages.game.details;

import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.game.modal.UpgradeModalPage;
import pl.kargolek.process.enums.Building;

/**
 * @author Karol Kuta-Orlowicz
 */
public interface BuildingDetailsPageable {

    BasePage open();
    UpgradeModalPage clickUpgradeButton();
    boolean isUpgradeButtonNotAvailable();
    String getHeaderText();
    Building getBuildingType();
}
