package pl.kargolek.pages.wallet.primary.popover;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.wallet.primary.settings.AddNetworkSettingsPage;

import java.time.Duration;

public class SelectNetworkPopover extends PopoverBasePage {

    @FindBy(xpath = ".//div[contains(@class, 'multichain-network-list-menu-content-wrapper')]//button[contains(text(), 'Add network')]")
    private WebElement addNetworkButton;

    public SelectNetworkPopover(WebDriver driver) {
        super(driver);
    }

    public AddNetworkSettingsPage clickAddNetworkButton() {
        this.waitForElementClickable(this.addNetworkButton, Duration.ofSeconds(20))
                .click();
        return new AddNetworkSettingsPage(this.driver);
    }
}
