package pl.kargolek.pages.game.modal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;

/**
 * @author Karol Kuta-Orlowicz
 */
public class UpgradeModalPage extends BasePage {

    @FindBy(xpath = "//div[text()='Required Materials']//..//button[text()='Upgrade']")
    private WebElement upgradeButton;

    @FindBy(xpath = "//div[text()='Required Materials']//..//button[text()='Open queue']")
    private WebElement openQueueButton;


    public UpgradeModalPage(WebDriver driver) {
        super(driver);
    }

    public BuildingQueueModalPage clickUpgradeButton(){
        this.waitForElementsVisibilityIgnoreTimeout(upgradeButton, getTimeoutDefault())
                .forEach(WebElement::click);
        return new BuildingQueueModalPage(driver);
    }

    public boolean isUpgradeButtonAvailable(){
        return this.waitForElementsVisibilityIgnoreTimeout(upgradeButton, getTimeout10Sec())
                .size() > 0;
    }

    public boolean isOpenQueueButtonNotAvailable(){
        return waitForElementsVisibilityIgnoreTimeout(openQueueButton, getTimeout5Sec()).isEmpty();
    }
}
