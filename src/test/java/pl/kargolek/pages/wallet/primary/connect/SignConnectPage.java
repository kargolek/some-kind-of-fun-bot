package pl.kargolek.pages.wallet.primary.connect;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.wallet.primary.main.WalletPrimaryPage;

import java.time.Duration;

public class SignConnectPage extends BasePage {

    @FindBy(xpath = "//button[@data-testid='page-container-footer-next' and text()='Sign']")
    private WebElement clickSignButton;

    public SignConnectPage(WebDriver driver) {
        super(driver);
    }

    public WalletPrimaryPage clickSignButton() {
        this.waitForElementClickable(clickSignButton, Duration.ofSeconds(20)).click();
        return new WalletPrimaryPage(this.driver);
    }
}
