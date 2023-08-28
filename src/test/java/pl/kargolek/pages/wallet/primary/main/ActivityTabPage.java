package pl.kargolek.pages.wallet.primary.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.wallet.primary.connect.SignConnectPage;

import java.time.Duration;

public class ActivityTabPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'transaction-list-item--unconfirmed')]//*[@data-testid='list-item-title' and contains(text(), 'Signature')]")
    private WebElement unconfirmedTransactionFirstItem;
    public ActivityTabPage(WebDriver driver) {
        super(driver);
    }

    public SignConnectPage clickSignatureUnconfirmedTransaction(){
        this.waitForElementClickable(unconfirmedTransactionFirstItem, Duration.ofSeconds(15))
                .click();
        return new SignConnectPage(this.driver);
    }

}
