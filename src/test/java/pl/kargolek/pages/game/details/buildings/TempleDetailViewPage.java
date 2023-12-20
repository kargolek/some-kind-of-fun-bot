package pl.kargolek.pages.game.details.buildings;

import org.openqa.selenium.WebDriver;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.game.details.BaseDetailViewPage;
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
}
