package pl.kargolek.pages.game.modal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import pl.kargolek.pages.BasePage;

import java.time.Duration;

public class TroopsModalPage extends BasePage {

    private final By maxButton = RelativeLocator
            .with(By.xpath("//button[text()='Max']"))
            .below(By.xpath("//div[text()='Your Troops']"));

    private final By attackButton = RelativeLocator
            .with(By.xpath("//button[text()='Attack']"))
            .below(By.xpath("//div[text()='Your Troops']"));

    private final By selectAllButton = RelativeLocator
            .with(By.xpath("//button[text()='Select All Units']"))
            .below(By.xpath("//div[text()='Your Troops']"));

    public TroopsModalPage(WebDriver driver) {
        super(driver);
    }

    public TroopsModalPage clickAllMaxButtons(){
        waitForElementsVisibilityIgnoreTimeout(maxButton, Duration.ofSeconds(7))
                .forEach(WebElement::click);
        return this;
    }

    public TroopsModalPage clickAttackButton(){
        waitForElementClickable(attackButton, getTimeoutDefault())
                .click();
        return this;
    }

    public TroopsModalPage clickSelectAllUnits() throws InterruptedException {
        Thread.sleep(2000);
        waitForElementClickable(selectAllButton, getTimeoutDefault())
                .click();
        return this;
    }
}
