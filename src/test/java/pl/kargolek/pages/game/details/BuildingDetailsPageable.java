package pl.kargolek.pages.game.details;

import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.game.modal.UpgradeModalPage;

/**
 * @author Karol Kuta-Orlowicz
 */
public interface BuildingDetailsPageable {

    BasePage open();
    UpgradeModalPage clickUpgradeButton();
    boolean isUpgradeButtonNotAvailable();
    String getHeaderText();
    String extractHeaderTextPattern();
}
