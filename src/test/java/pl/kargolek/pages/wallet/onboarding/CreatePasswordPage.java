package pl.kargolek.pages.wallet.onboarding;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;

import java.time.Duration;

public class CreatePasswordPage extends BasePage {

    @FindBy(xpath = ".//input[@data-testid='create-password-new']")
    private WebElement passwordInput;

    @FindBy(xpath = ".//input[@data-testid='create-password-confirm']")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = ".//input[@data-testid='create-password-terms']")
    private WebElement termsCheckbox;

    @FindBy(xpath = ".//button[@data-testid='create-password-import']")
    private WebElement confirmPasswordButton;

    public CreatePasswordPage(WebDriver driver) {
        super(driver);
    }

    public OnBoardingDonePage setupPassword(String password) {
        this.waitForElementVisibility(passwordInput, Duration.ofSeconds(10))
                .sendKeys(password);
        this.waitForElementVisibility(confirmPasswordInput, Duration.ofSeconds(10))
                .sendKeys(password);
        this.waitForElementClickable(termsCheckbox, Duration.ofSeconds(10))
                .click();
        this.waitForElementClickable(confirmPasswordButton, Duration.ofSeconds(10))
                .click();
        return new OnBoardingDonePage(this.driver);
    }
}
