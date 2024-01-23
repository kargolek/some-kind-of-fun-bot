package pl.kargolek.pages.game.details.buildings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;

/**
 * @author Karol Kuta-Orlowicz
 */
public class TheatreDetailViewPage extends BasePage {

    @FindBy(xpath = "//button[contains(text(), 'Free Spin')]")
    private WebElement freeSpinButton;

    @FindBy(xpath = "//button[contains(text(), 'Spin again')]")
    private WebElement spinAgainButton;

    public TheatreDetailViewPage(WebDriver driver) {
        super(driver);
    }

    public TheatreDetailViewPage clickSpinButtonIfEnable(){
        var button = this.waitForElementVisibility(freeSpinButton, getTimeoutDefault());
        if (button.isEnabled()){
            button.click();
            this.waitForElementVisibility(spinAgainButton, getTimeoutDefault());
        }
        return this;
    }
}
