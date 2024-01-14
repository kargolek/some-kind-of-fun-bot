package pl.kargolek.pages.game.details.buildings;

import org.openqa.selenium.WebDriver;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.game.details.BaseDetailViewPage;
import pl.kargolek.process.enums.Building;
import pl.kargolek.util.TestProperty;

/**
 * @author Karol Kuta-Orlowicz
 */
public class SenateDetailViewPage extends BaseDetailViewPage {

    public SenateDetailViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SenateDetailViewPage open() {
        driver.get(TestProperty.getInstance().getSenateURL());
        return this;
    }

    @Override
    public Building getBuildingType() {
        return Building.SENATE;
    }

}
