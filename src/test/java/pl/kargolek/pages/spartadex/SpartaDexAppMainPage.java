package pl.kargolek.pages.spartadex;

import org.openqa.selenium.WebDriver;
import pl.kargolek.pages.BasePage;

public class SpartaDexAppMainPage extends BasePage {


    public SpartaDexAppMainPage(WebDriver driver) {
        super(driver);
    }

    public SideNavigationPage getSideNavigationPage(){
        return new SideNavigationPage(this.driver);
    }

    public SpartaDexAppMainPage open(){
        this.driver.get("https://app.spartadex.io/");
        return this;
    }
}
