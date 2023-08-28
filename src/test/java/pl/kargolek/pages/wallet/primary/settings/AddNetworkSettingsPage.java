package pl.kargolek.pages.wallet.primary.settings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.wallet.primary.popover.ConfirmationPopoverPage;

import java.time.Duration;

public class AddNetworkSettingsPage extends BasePage {

    @FindBy(xpath = ".//div[contains(@class, 'add-network__main-container')]")
    private WebElement networksContainers;

    public AddNetworkSettingsPage(WebDriver driver) {
        super(driver);
    }

    public ConfirmationPopoverPage clickAddNetworkByName(String networkName){
        this.waitForElementVisibility(networksContainers, Duration.ofSeconds(10))
                .findElement(By.xpath(String.format("//*[text()='%s']//../../..//button[contains(@class, 'add-network__add-button')]", networkName)))
                .click();
        return new ConfirmationPopoverPage(this.driver);
    }
}
