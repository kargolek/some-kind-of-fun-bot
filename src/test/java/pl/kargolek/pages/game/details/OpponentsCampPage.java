package pl.kargolek.pages.game.details;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import pl.kargolek.pages.game.modal.TroopsModalPage;
import pl.kargolek.util.TestProperty;

public class OpponentsCampPage extends BaseDetailViewPage {
    private final By attackOpponentsCampButton = RelativeLocator
            .with(By.xpath("//button[text()='Attack Barbarian Camp']"))
            .below(By.xpath("//*[contains(text(), 'Rewards from defeating barbarians')]"));
    public OpponentsCampPage(WebDriver driver) {
        super(driver);
    }

    public OpponentsCampPage open(){
        driver.get(TestProperty.getInstance().getGameOpponentsCampURL());
        return this;
    }

    public TroopsModalPage clickAttackButton(){
        waitForElementClickable(attackOpponentsCampButton, getTimeoutDefault())
                .click();
        return new TroopsModalPage(this.driver);
    }
}
