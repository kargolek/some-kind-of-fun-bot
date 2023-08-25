package pl.kargolek.pages.spartadex.details;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.locators.RelativeLocator;
import pl.kargolek.process.enums.Soldier;

public class BarracksDetailPage extends BaseDetailViewPage {

    private final String recruitSoldierButton = "//div[text()='%s']/../../..//button[contains(text(), 'Recruit')]";
    private final String maxSoldierButton = "//div[text()='%s']/../../..//button[text()='Max']";
    private final By unitsValue = RelativeLocator.with(By.tagName("span"))
            .below(By.xpath("//*[text()='Your Units']"));
    @FindBy(xpath = "//div//h1[text()='Barracks']/..//div[contains(text(), 'Slots occupied:')]")
    private WebElement slotsOccupied;
    @FindBy(xpath = "//button[text()='Open Queue']")
    private WebElement openQueueButton;

    public BarracksDetailPage(WebDriver driver) {
        super(driver);
    }

    public BarracksDetailPage open() {
        String url = "https://app.spartadex.io/game/barracks";
        driver.get(url);
        waitForTitleDetailViewHeader("barracks", "Barracks");
        return this;
    }

    public String getSlotsOccupiedText() {
        waitForElementVisibility(unitsValue, getTimeoutDefault());
        return waitForElementVisibility(slotsOccupied, getTimeoutDefault())
                .getText();
    }

    public BarracksDetailPage clickRecruitButton(Soldier soldier) {
        var element = waitForElementVisibility(By.xpath(String.format(recruitSoldierButton, soldier)),
                getTimeoutDefault());
        if (element.isEnabled())
            element.click();
        return this;
    }

    public BarracksDetailPage clickMaxButton(Soldier soldier) {
        waitForElementVisibility(By.xpath(String.format(maxSoldierButton, soldier)), getTimeoutDefault())
                .click();
        return this;
    }

    public SoldierQueueModalPage clickOpenQueueButton() {
        waitForElementClickable(openQueueButton, getTimeoutDefault())
                .click();
        return new SoldierQueueModalPage(this.driver);
    }

    public Integer getAmountSoldierText(Soldier soldier) {
        return Integer.valueOf(waitForElementVisibility(RelativeLocator.with(unitsValue)
                .toLeftOf(By.xpath(String.format("//img[contains(@src, '/assets/%s')]",
                        soldier.toString().toLowerCase()))), getTimeoutDefault())
                .getText());
    }

}
