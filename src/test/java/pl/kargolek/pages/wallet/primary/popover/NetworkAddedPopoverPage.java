package pl.kargolek.pages.wallet.primary.popover;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;

import java.time.Duration;

public class NetworkAddedPopoverPage extends BasePage {

    @FindBy(css = ".home__new-network-added__switch-to-button")
    private WebElement switchToNetworkButton;

    public NetworkAddedPopoverPage(WebDriver driver) {
        super(driver);
    }

    public InfoPopoverPage clickSwitchToNetworkButton(){
        this.waitForElementClickable(switchToNetworkButton, Duration.ofSeconds(10))
                .click();
        return new InfoPopoverPage(this.driver);
    }
}
