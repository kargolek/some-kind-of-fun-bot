package pl.kargolek.pages.metamask.wallet.connect;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.metamask.wallet.main.MetamaskMainPage;

import java.time.Duration;

public class SignConnectPage extends BasePage {

    @FindBy(xpath = "//button[@data-testid='page-container-footer-next' and text()='Sign']")
    private WebElement clickSignButton;

    public SignConnectPage(WebDriver driver) {
        super(driver);
    }

    public MetamaskMainPage clickSignButton() {
        this.waitForElementClickable(clickSignButton, Duration.ofSeconds(20)).click();
        return new MetamaskMainPage(this.driver);
    }
}
