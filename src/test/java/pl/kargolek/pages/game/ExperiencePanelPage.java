package pl.kargolek.pages.game;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;

public class ExperiencePanelPage extends BasePage {

    @FindBy(xpath = "//div[@id='resWrap']/..//div[contains(text(),'/')]")
    private WebElement experienceProgressBarValue;

    public ExperiencePanelPage(WebDriver driver) {
        super(driver);
    }

    public String getWoodValue() {
        return waitForElementVisibility(getResourceByLocator("wood"), getTimeoutDefault()).getText();
    }

    public String getStoneValue() {
        return waitForElementVisibility(getResourceByLocator("stone"), getTimeoutDefault()).getText();
    }

    public String getGoldValue() {
        return waitForElementVisibility(getResourceByLocator("gold"), getTimeoutDefault()).getText();
    }

    public String getGemValue() {
        return waitForElementVisibility(getResourceByLocator("gem"), getTimeoutDefault()).getText();
    }

    public String getExperienceValue() {
        return waitForElementVisibility(experienceProgressBarValue, getTimeoutDefault()).getText();
    }

    private By getResourceByLocator(String typeResource) {
        String resourceLocator = "//*[name()='svg']/*[name()='rect' and contains(@fill, '%s')]/../../span";
        return By.xpath(String.format(resourceLocator, typeResource));
    }
}
