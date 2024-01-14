package pl.kargolek.pages.game.details.buildings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.locators.RelativeLocator;
import pl.kargolek.pages.game.details.BaseDetailViewPage;
import pl.kargolek.pages.game.modal.ReviewRecruitmentModalPage;
import pl.kargolek.pages.game.modal.SoldierQueueModalPage;
import pl.kargolek.process.enums.Building;
import pl.kargolek.process.enums.Soldier;
import pl.kargolek.util.TestProperty;

public class YourCampDetailPage extends BaseDetailViewPage {

    private final String recruitSoldierButton = "//div[text()='%s']/../../..//button[contains(text(), 'Recruit')]";
    private final String maxSoldierButton = "//div[text()='%s']/../../..//button[text()='Max']";
    private final By unitsValue = RelativeLocator.with(By.tagName("span"))
            .below(By.xpath("//*[text()='Your Units']"));
    @FindBy(xpath = "//div//h1[text()='Barracks']/..//div[contains(text(), 'Slots occupied:')]")
    private WebElement slotsOccupied;
    @FindBy(xpath = "//button[text()='Open Queue']")
    private WebElement openQueueButton;

    public YourCampDetailPage(WebDriver driver) {
        super(driver);
    }

    public YourCampDetailPage open() {
        driver.get(TestProperty.getInstance().getGameCampURL());
        waitForTitleDetailViewHeader("barracks", "Barracks");
        return this;
    }

    @Override
    public Building getBuildingType() {
        return Building.BARRACKS;
    }

    public String getSlotsOccupiedText() {
        waitForElementVisibility(unitsValue, getTimeoutDefault());
        return waitForElementVisibility(slotsOccupied, getTimeoutDefault())
                .getText();
    }

    public ReviewRecruitmentModalPage clickRecruitButton(Soldier soldier) throws InterruptedException {
        Thread.sleep(500);
        var element = waitForElementVisibility(By.xpath(String.format(recruitSoldierButton, soldier)),
                getTimeoutDefault());
        if (element.isEnabled())
            element.click();
        return new ReviewRecruitmentModalPage(driver);
    }

    public YourCampDetailPage clickMaxButton(Soldier soldier) {
        var button  = waitForElementVisibility(By.xpath(String.format(maxSoldierButton, soldier)), getTimeoutDefault());
        Actions actions = new Actions(driver);
        actions.moveToElement(button)
                .click()
                .build()
                .perform();
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

    public boolean isRecruitButtonEnable(Soldier soldier) throws InterruptedException {
        Thread.sleep(1000);
        return waitForElementVisibility(By.xpath(String.format(recruitSoldierButton, soldier)),
                getTimeoutDefault()).isEnabled();
    }

}
