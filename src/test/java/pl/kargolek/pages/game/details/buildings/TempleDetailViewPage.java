package pl.kargolek.pages.game.details.buildings;

import org.openqa.selenium.WebDriver;
import pl.kargolek.pages.game.details.BaseDetailViewPage;
import pl.kargolek.process.enums.Building;
import pl.kargolek.util.TestProperty;

/**
 * @author Karol Kuta-Orlowicz
 */
public class TempleDetailViewPage extends BaseDetailViewPage {

    public TempleDetailViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public TempleDetailViewPage open() {
        driver.get(TestProperty.getInstance().getTempleURL());
        return this;
    }

    @Override
    public Building getBuildingType() {
        return Building.TEMPLE;
    }


}
