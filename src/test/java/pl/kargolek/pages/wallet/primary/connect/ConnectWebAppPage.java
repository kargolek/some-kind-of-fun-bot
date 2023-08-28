package pl.kargolek.pages.wallet.primary.connect;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.wallet.primary.main.WalletPrimaryPage;

import java.time.Duration;

public class ConnectWebAppPage extends BasePage {

    @FindBy(xpath = "//button[@data-testid='page-container-footer-next' and text()='Next']")
    private WebElement clickNextButton;

    @FindBy(xpath = "//button[@data-testid='page-container-footer-next' and text()='Connect']")
    private WebElement clickConnectButton;

    public ConnectWebAppPage(WebDriver driver) {
        super(driver);
    }

    public ConnectWebAppPage clickNextButton() {
        this.waitForElementClickable(clickNextButton, Duration.ofSeconds(10))
                .click();
        return this;
    }

    public WalletPrimaryPage clickConnectButton() {
        this.waitForElementClickable(clickConnectButton, Duration.ofSeconds(10))
                .click();
        return new WalletPrimaryPage(this.driver);
    }

}
