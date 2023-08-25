package pl.kargolek.pages.spartadex.modal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;

import java.time.Duration;

public class ConnectWalletModalPage extends BasePage {

    @FindBy(xpath = "//button[text()='MetaMask']")
    private WebElement metamaskButton;

    public ConnectWalletModalPage(WebDriver driver) {
        super(driver);
    }

    public void clickMetamaskButton(){
        this.waitForElementClickable(metamaskButton, Duration.ofSeconds(10))
                .click();
    }
}
