package pl.kargolek.pages.game.modal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pl.kargolek.pages.BasePage;

/**
 * @author Karol Kuta-Orlowicz
 */
public class BuildingQueueModalPage extends BasePage {

    private final By upgradeItem = By.xpath("//div[text()='Builds in progress']/../..//img");
    private final By itemsQueueContainer = By.xpath("//div[contains(text(), 'Builds in progress')]/../..");

    public BuildingQueueModalPage(WebDriver driver) {
        super(driver);
    }

    public Integer getNumberOfUpgradedItems() {
        return waitForElementsVisibilityIgnoreTimeout(upgradeItem, getTimeout5Sec()).size();
    }

    public String getItemsQueueContainerText(){
        return waitForElementVisibility(itemsQueueContainer, getTimeoutDefault()).getText();
    }
}
