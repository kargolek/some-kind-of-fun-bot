package pl.kargolek.pages.spartadex.modal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.spartadex.SpartaDexAppMainPage;

import java.time.Duration;

public class ChooseCityModalPage extends BasePage {

    @FindBy(xpath = ".//*[text()='Choose Your Polis']/..//span[text()='ENTER']")
    private WebElement enterPolisButton;


    public ChooseCityModalPage(WebDriver driver) {
        super(driver);
    }

    public SpartaDexAppMainPage clickEnterButton() {
        this.waitForElementClickable(this.enterPolisButton, Duration.ofSeconds(10))
                .click();
        return new SpartaDexAppMainPage(this.driver);
    }
}
