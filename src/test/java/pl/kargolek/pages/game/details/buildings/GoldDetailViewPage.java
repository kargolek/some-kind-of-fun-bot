package pl.kargolek.pages.game.details.buildings;

import org.openqa.selenium.WebDriver;
import pl.kargolek.pages.game.details.BaseDetailViewPage;
import pl.kargolek.util.TestProperty;

/**
 * @author Karol Kuta-Orlowicz
 */
public class GoldDetailViewPage extends BaseDetailViewPage {

    public GoldDetailViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public GoldDetailViewPage open() {
        driver.get(TestProperty.getInstance().getGoldURL());
        return this;
    }
}
