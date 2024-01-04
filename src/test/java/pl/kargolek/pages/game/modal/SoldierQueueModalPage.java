package pl.kargolek.pages.game.modal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.game.details.buildings.YourCampDetailPage;

import java.time.Duration;
import java.util.List;

public class SoldierQueueModalPage extends BasePage {
    @FindBy(xpath = "//div[text()='Recruitments in progress']")
    private WebElement titleHeader;

    @FindBy(xpath = "//div[text()='Recruitments in progress']/../../../div/*[local-name()='svg']")
    private WebElement closeModalButton;
    @FindBy(xpath = "//div[text()='Recruitments in progress']/../..//*[text()='Claim']")
    private List<WebElement> claimButtons;

    public SoldierQueueModalPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getTitleHeader() {
        return waitForElementVisibility(titleHeader, getTimeoutDefault());
    }

    public YourCampDetailPage clickCloseModalButton() {
        getTitleHeader();
        waitForElementClickable(closeModalButton, getTimeoutDefault())
                .click();
        return new YourCampDetailPage(driver);
    }

    public SoldierQueueModalPage clickAllClaimButtons() {
        getTitleHeader();
        waitForElementsVisibilityIgnoreTimeout(claimButtons, Duration.ofSeconds(5))
                .forEach(WebElement::click);
        return this;
    }

}
