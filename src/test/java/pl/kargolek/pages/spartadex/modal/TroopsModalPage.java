package pl.kargolek.pages.spartadex.modal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import pl.kargolek.pages.BasePage;

public class TroopsModalPage extends BasePage {

    private final By maxButton = RelativeLocator
            .with(By.xpath("//button[text()='Max']"))
            .below(By.xpath("//div[text()='Your Troops']"));

    private final By attackButton = RelativeLocator
            .with(By.xpath("//button[text()='Attack']"))
            .below(By.xpath("//div[text()='Your Troops']"));

    public TroopsModalPage(WebDriver driver) {
        super(driver);
    }

    public TroopsModalPage clickAllMaxButtons(){
        waitForElementAllVisibility(maxButton, getTimeoutDefault())
                .forEach(WebElement::click);
        return this;
    }

    public TroopsModalPage clickAttackButton(){
        waitForElementClickable(attackButton, getTimeoutDefault())
                .click();
        return this;
    }
}
