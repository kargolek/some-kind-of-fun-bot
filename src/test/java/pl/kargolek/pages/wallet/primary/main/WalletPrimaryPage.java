package pl.kargolek.pages.wallet.primary.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.wallet.primary.connect.ConnectWebAppPage;
import pl.kargolek.pages.wallet.primary.connect.SignConnectPage;
import pl.kargolek.pages.wallet.primary.popover.SelectNetworkPopover;

import java.time.Duration;

public class WalletPrimaryPage extends BasePage {

    @FindBy(xpath = ".//li[@data-testid='home__asset-tab']/button")
    private WebElement tokensTab;
    @FindBy(xpath = ".//li[@data-testid='home__activity-tab']/button")
    private WebElement activityTab;

    @FindBy(xpath = ".//button[@data-testid='network-display']")
    private WebElement networkDisplayDropdown;

    public WalletPrimaryPage(WebDriver driver) {
        super(driver);
    }

    public ActivityTabPage clickActivityTab(){
        this.waitForElementClickable(this.activityTab, Duration.ofSeconds(10))
                .click();
        return new ActivityTabPage(this.driver);
    }

    public WalletPrimaryPage waitForPage(){
        this.waitForElementVisibility(tokensTab, Duration.ofSeconds(15));
        return this;
    }

    public SelectNetworkPopover clickNetworkDisplayDropdown(){
        this.waitForElementClickable(this.networkDisplayDropdown, Duration.ofSeconds(10))
                .click();
        return new SelectNetworkPopover(this.driver);
    }

    public ConnectWebAppPage getConnectWebAppPage(){
        return new ConnectWebAppPage(this.driver);
    }

    public SignConnectPage getSignWebAppPage(){
        return new SignConnectPage(this.driver);
    }
}
