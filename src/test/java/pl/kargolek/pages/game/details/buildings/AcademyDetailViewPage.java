package pl.kargolek.pages.game.details.buildings;

import org.openqa.selenium.WebDriver;
import pl.kargolek.pages.game.details.BaseDetailViewPage;
import pl.kargolek.util.TestProperty;

/**
 * @author Karol Kuta-Orlowicz
 */
public class AcademyDetailViewPage extends BaseDetailViewPage {

    public AcademyDetailViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AcademyDetailViewPage open() {
        driver.get(TestProperty.getInstance().getAcademyURL());
        return this;
    }

    @Override
    public String extractHeaderTextPattern() {
        return "Academy";
    }
}
