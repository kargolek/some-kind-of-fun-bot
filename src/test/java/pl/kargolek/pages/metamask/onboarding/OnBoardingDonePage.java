package pl.kargolek.pages.metamask.onboarding;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.metamask.wallet.popover.InfoPopoverPage;

import java.time.Duration;

public class OnBoardingDonePage extends BasePage {

    @FindBy(xpath = ".//button[@data-testid='onboarding-complete-done']")
    private WebElement confirmDoneButton;

    @FindBy(xpath = ".//button[@data-testid='pin-extension-next']")
    private WebElement pinExtNextButton;

    @FindBy(xpath = ".//button[@data-testid='pin-extension-done']")
    private WebElement pinExtConfirmButton;

    public OnBoardingDonePage(WebDriver driver) {
        super(driver);
    }

    public InfoPopoverPage confirmCompleteWalletCreate(){
        this.waitForElementClickable(confirmDoneButton, Duration.ofSeconds(10))
                .click();
        this.waitForElementClickable(pinExtNextButton, Duration.ofSeconds(10))
                .click();
        this.waitForElementClickable(pinExtConfirmButton, Duration.ofSeconds(10))
                .click();
        return new InfoPopoverPage(this.driver);
    }
}
