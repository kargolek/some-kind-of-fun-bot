package pl.kargolek.pages.game.details;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.game.modal.UpgradeModalPage;

import java.time.Duration;
import java.util.List;

public abstract class BaseDetailViewPage extends BasePage implements ItemDetailsPageable {

    @FindBy(xpath = "//button[text()='Upgrade']")
    private WebElement upgradeButton;

    public BaseDetailViewPage(WebDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleDetailViewHeader(String partURL, String titleName) {
        this.waitForContainsURL(partURL, Duration.ofSeconds(20));
        String titleHeader = "//div//h1[text()='%s']";
        return this.waitForElementVisibility(By.xpath(String.format(titleHeader, titleName)), Duration.ofSeconds(20));
    }

    @Override
    public UpgradeModalPage clickUpgradeButton() {
        waitForElementClickable(upgradeButton, getTimeoutDefault())
                    .click();
        return new UpgradeModalPage(this.driver);
    }

    @Override
    public boolean isUpgradeButtonNotAvailable() {
        return waitForElementsVisibilityIgnoreTimeout(List.of(upgradeButton), getTimeout5Sec())
                .isEmpty();
    }

    @Override
    public String getHeaderText() {
        var xpath = String.format("//h1[contains(text(), '%s')]", extractHeaderTextPattern());
        return waitForElementVisibility(By.xpath(xpath), getTimeoutDefault())
                .getText();
    }
}
