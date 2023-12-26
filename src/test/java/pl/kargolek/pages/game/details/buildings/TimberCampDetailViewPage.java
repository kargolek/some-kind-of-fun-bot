package pl.kargolek.pages.game.details.buildings;

import org.openqa.selenium.WebDriver;
import pl.kargolek.pages.game.details.BaseDetailViewPage;
import pl.kargolek.util.TestProperty;

/**
 * @author Karol Kuta-Orlowicz
 */
public class TimberCampDetailViewPage extends BaseDetailViewPage {

    public TimberCampDetailViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public TimberCampDetailViewPage open() {
        driver.get(TestProperty.getInstance().getTimberURL());
        return this;
    }

    @Override
    public String extractHeaderTextPattern() {
        return "Timber Camp";
    }
}
