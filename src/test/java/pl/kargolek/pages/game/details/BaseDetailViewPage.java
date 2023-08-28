package pl.kargolek.pages.game.details;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.kargolek.pages.BasePage;

import java.time.Duration;

public class BaseDetailViewPage extends BasePage {

    private final String titleHeader = "//div//h1[text()='%s']";

    public BaseDetailViewPage(WebDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleDetailViewHeader(String partURL, String titleName){
        this.waitForContainsURL(partURL, Duration.ofSeconds(20));
        return this.waitForElementVisibility(By.xpath(String.format(titleHeader, titleName)), Duration.ofSeconds(20));
    }
}
