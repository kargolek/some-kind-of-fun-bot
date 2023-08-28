package pl.kargolek.pages.game;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.game.details.OpponentsCampPage;
import pl.kargolek.pages.game.details.YourCampDetailPage;
import pl.kargolek.pages.game.modal.ConnectWalletModalPage;

public class SideNavigationPage extends BasePage {

    @FindBy(xpath = "//ul//div[text()='Barracks']")
    private WebElement campButton;

    @FindBy(xpath = "//ul//div[text()='Barbarian Camp']")
    private WebElement opponentsCampButton;

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

    public YourCampDetailPage clickCampButton(){
        this.waitForElementClickable(campButton, getTimeoutDefault())
                .click();
        return new YourCampDetailPage(this.driver);
    }

    public OpponentsCampPage clickOpponentsCampButton(){
        this.waitForElementClickable(opponentsCampButton, getTimeoutDefault())
                .click();
        return new OpponentsCampPage(this.driver);
    }
}
