package pl.kargolek.pages.game;

import org.openqa.selenium.WebDriver;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.game.modal.AgreementsModalPage;
import pl.kargolek.util.TestProperty;

public class GameMainPage extends BasePage {


    public GameMainPage(WebDriver driver) {
        super(driver);
    }

    public GameMainPage open(){
        this.driver.get(TestProperty.getInstance().getGameURL());
        return this;
    }

    public SideNavigationPage getSideNavigationPage(){
        return new SideNavigationPage(this.driver);
    }

    public ExperiencePanelPage getExperiencePanelPage(){
        return new ExperiencePanelPage(this.driver);
    }

    public AgreementsModalPage getAgreementsModalPage(){
        return new AgreementsModalPage(this.driver);
    }
}
