package pl.kargolek.pages.metamask.wallet.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.metamask.wallet.connect.ConnectWebAppPage;
import pl.kargolek.pages.metamask.wallet.connect.SignConnectPage;
import pl.kargolek.pages.metamask.wallet.popover.SelectNetworkPopover;

import java.time.Duration;

public class MetamaskMainPage extends BasePage {

    @FindBy(xpath = ".//li[@data-testid='home__asset-tab']/button")
    private WebElement tokensTab;
    @FindBy(xpath = ".//li[@data-testid='home__activity-tab']/button")
    private WebElement activityTab;

    @FindBy(xpath = ".//button[@data-testid='network-display']")
    private WebElement networkDisplayDropdown;

    public MetamaskMainPage(WebDriver driver) {
        super(driver);
    }

    public ActivityTabPage clickActivityTab(){
        this.waitForElementClickable(this.activityTab, Duration.ofSeconds(10))
                .click();
        return new ActivityTabPage(this.driver);
    }

    public MetamaskMainPage waitForPage(){
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
