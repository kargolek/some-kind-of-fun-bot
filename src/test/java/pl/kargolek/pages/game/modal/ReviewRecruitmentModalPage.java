package pl.kargolek.pages.game.modal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.game.details.buildings.YourCampDetailPage;

import java.time.Duration;
import java.util.List;

/**
 * @author Karol Kuta-Orlowicz
 */
public class ReviewRecruitmentModalPage extends BasePage {

    @FindBy(xpath = "//div[text()='Review recruitment']//..//button[text()='Recruit']")
    private WebElement recruitButton;

    @FindBy(xpath = "//div[text()='Review recruitment']//..//..//div/*[local-name()='svg']")
    private WebElement closeButton;

    public ReviewRecruitmentModalPage(WebDriver driver) {
        super(driver);
    }

    public boolean isReviewRecruitModalOpened(){
        return waitForElementsVisibilityIgnoreTimeout(List.of(recruitButton), Duration.ofSeconds(5))
                .size() > 0;
    }

    public SoldierQueueModalPage clickRecruitModalButton() {
        waitForElementClickable(recruitButton, getTimeoutDefault())
                .click();
        return new SoldierQueueModalPage(driver);
    }

    public YourCampDetailPage clickCloseModalButton() {
        waitForElementClickable(closeButton, getTimeoutDefault())
                .click();
        return new YourCampDetailPage(driver);
    }
}
