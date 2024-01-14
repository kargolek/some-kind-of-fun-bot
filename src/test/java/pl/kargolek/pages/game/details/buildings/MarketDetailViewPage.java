package pl.kargolek.pages.game.details.buildings;

import org.openqa.selenium.WebDriver;
import pl.kargolek.pages.game.details.BaseDetailViewPage;
import pl.kargolek.process.enums.Building;
import pl.kargolek.util.TestProperty;

/**
 * @author Karol Kuta-Orlowicz
 */
public class MarketDetailViewPage extends BaseDetailViewPage {

    public MarketDetailViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MarketDetailViewPage open() {
        driver.get(TestProperty.getInstance().getMarketURL());
        return this;
    }

    @Override
    public Building getBuildingType() {
        return Building.MARKET;
    }

}
