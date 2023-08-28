package pl.kargolek.pages.wallet.onboarding;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;

import java.time.Duration;

public class MetricsPage extends BasePage {

    @FindBy(xpath = ".//button[@data-testid='metametrics-no-thanks']")
    private WebElement disagreeButton;

    public MetricsPage(WebDriver driver) {
        super(driver);
    }

    public RecoveryPhrasePage clickDisagreeButton(){
        this.waitForElementClickable(disagreeButton, Duration.ofSeconds(10)).click();
        return new RecoveryPhrasePage(this.driver);
    }


}
