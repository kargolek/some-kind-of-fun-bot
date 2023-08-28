package pl.kargolek.pages.wallet.onboarding;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;

import java.time.Duration;

public class WelcomePage extends BasePage {

    @FindBy(id = "onboarding__terms-checkbox")
    private WebElement onBoardingTermsCheckbox;

    @FindBy(xpath = ".//button[@data-testid='onboarding-import-wallet']")
    private WebElement importWalletButton;

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    public WelcomePage clickOnBoardingCheckbox(){
        this.waitForElementVisibility(onBoardingTermsCheckbox, Duration.ofSeconds(10))
                .click();
        return this;
    }

    public MetricsPage clickImportWalletButton(){
        this.waitForElementClickable(importWalletButton, Duration.ofSeconds(10))
                .click();
        return new MetricsPage(this.driver);
    }

}
