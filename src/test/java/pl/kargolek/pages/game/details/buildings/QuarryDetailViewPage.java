package pl.kargolek.pages.game.details.buildings;

import org.openqa.selenium.WebDriver;
import pl.kargolek.pages.game.details.BaseDetailViewPage;
import pl.kargolek.util.TestProperty;

/**
 * @author Karol Kuta-Orlowicz
 */
public class QuarryDetailViewPage extends BaseDetailViewPage {

    public QuarryDetailViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public QuarryDetailViewPage open() {
        driver.get(TestProperty.getInstance().getQuarryURL());
        return this;
    }
}
