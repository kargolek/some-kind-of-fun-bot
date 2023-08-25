package pl.kargolek.pages.spartadex;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.spartadex.details.BarbariansCampPage;
import pl.kargolek.pages.spartadex.details.BarracksDetailPage;
import pl.kargolek.pages.spartadex.modal.ConnectWalletModalPage;

public class SideNavigationPage extends BasePage {

    @FindBy(xpath = "//ul//div[text()='Barracks']")
    private WebElement barracksButton;

    @FindBy(xpath = "//ul//div[text()='Barbarian Camp']")
    private WebElement barbariansCamp;

    @FindBy(xpath = "//button[text()='Connect wallet']")
    private WebElement connectWalletButton;

    public SideNavigationPage(WebDriver driver) {
        super(driver);
    }

    public ConnectWalletModalPage clickConnectWalletButton(){
        this.waitForElementClickable(connectWalletButton, getTimeoutDefault())
                .click();
        return new ConnectWalletModalPage(this.driver);
    }

    public BarracksDetailPage clickBarracksButton(){
        this.waitForElementClickable(barracksButton, getTimeoutDefault())
                .click();
        return new BarracksDetailPage(this.driver);
    }


    public BarbariansCampPage clickBarbariansCampButton(){
        this.waitForElementClickable(barbariansCamp, getTimeoutDefault())
                .click();
        return new BarbariansCampPage(this.driver);
    }
}
