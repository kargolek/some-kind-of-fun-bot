package pl.kargolek.pages.spartadex.details;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.spartadex.modal.TroopsModalPage;

public class BarbariansCampPage extends BaseDetailViewPage {

    private final String url = "https://app.spartadex.io/game/barbarian-camp";
    private final By attackBarbariansCampButton = RelativeLocator
            .with(By.xpath("//button[text()='Attack Barbarian Camp']"))
            .below(By.xpath("//*[contains(text(), 'Rewards from defeating barbarians')]"));
    public BarbariansCampPage(WebDriver driver) {
        super(driver);
    }

    public BarbariansCampPage open(){
        driver.get(url);
        return this;
    }

    public TroopsModalPage clickAttackButton(){
        waitForElementClickable(attackBarbariansCampButton, getTimeoutDefault())
                .click();
        return new TroopsModalPage(this.driver);
    }
}
