package pl.kargolek.pages.game.details.buildings;

import org.openqa.selenium.WebDriver;
import pl.kargolek.pages.game.details.BaseDetailViewPage;
import pl.kargolek.process.enums.Building;
import pl.kargolek.util.TestProperty;

/**
 * @author Karol Kuta-Orlowicz
 */
public class WallDetailViewPage extends BaseDetailViewPage {

    public WallDetailViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WallDetailViewPage open() {
        driver.get(TestProperty.getInstance().getWallURL());
        return this;
    }

    @Override
    public Building getBuildingType() {
        return Building.WALL;
    }
}
