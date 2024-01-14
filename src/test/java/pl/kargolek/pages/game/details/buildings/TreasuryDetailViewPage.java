package pl.kargolek.pages.game.details.buildings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pl.kargolek.pages.game.details.BaseDetailViewPage;
import pl.kargolek.process.enums.Building;
import pl.kargolek.util.TestProperty;

/**
 * @author Karol Kuta-Orlowicz
 */
public class TreasuryDetailViewPage extends BaseDetailViewPage {

    public TreasuryDetailViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public TreasuryDetailViewPage open() {
        driver.get(TestProperty.getInstance().getTreasuryURL());
        return this;
    }

    @Override
    public Building getBuildingType() {
        return Building.TREASURY;
    }

    public String getWoodValue() {
        return waitForElementVisibility(getResourceByLocator("wood"), getTimeoutDefault()).getText();
    }

    public String getStoneValue() {
        return waitForElementVisibility(getResourceByLocator("stone"), getTimeoutDefault()).getText();
    }

    public String getGoldValue() {
        return waitForElementVisibility(getResourceByLocator("gold"), getTimeoutDefault()).getText();
    }

    private By getResourceByLocator(String typeResource) {
        String resourceLocator = "//span[text()='My Storage:']/../div[1]//" +
                "*[name()='svg']/*[name()='rect' and contains(@fill, '%s')]/../..";
        return By.xpath(String.format(resourceLocator, typeResource));
    }
}
