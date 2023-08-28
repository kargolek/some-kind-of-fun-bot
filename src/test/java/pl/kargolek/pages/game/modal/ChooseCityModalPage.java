package pl.kargolek.pages.game.modal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.game.GameMainPage;

import java.time.Duration;

public class ChooseCityModalPage extends BasePage {

    @FindBy(xpath = ".//*[text()='Choose Your Polis']/..//span[text()='ENTER']")
    private WebElement enterCityButton;

    public ChooseCityModalPage(WebDriver driver) {
        super(driver);
    }

    public GameMainPage clickEnterCityButton() {
        this.waitForElementClickable(this.enterCityButton, Duration.ofSeconds(10))
                .click();
        return new GameMainPage(this.driver);
    }
}
