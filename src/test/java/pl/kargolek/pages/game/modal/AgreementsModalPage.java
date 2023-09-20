package pl.kargolek.pages.game.modal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.dom.model.BackendNode;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;

/**
 * @author Karol Kuta-Orlowicz
 */
public class AgreementsModalPage extends BasePage {

    @FindBy(xpath = ".//button[@role='checkbox' and @id='1']")
    private WebElement termsUserLicenseCheckbox;

    @FindBy(xpath = ".//button[@role='checkbox' and @id='2']")
    private WebElement agreementsCheckbox;

    @FindBy(xpath = ".//button[@role='checkbox' and @id='1']/../..//button[text()='Accept']")
    private WebElement acceptButton;

    public AgreementsModalPage(WebDriver driver) {
        super(driver);
    }

    public AgreementsModalPage clickTermsUserLicenseCheckbox(){
        this.waitForElementClickable(termsUserLicenseCheckbox, getTimeoutDefault())
                .click();
        return this;
    }

    public AgreementsModalPage clickAgreementCheckbox(){
        this.waitForElementClickable(agreementsCheckbox, getTimeoutDefault())
                .click();
        return this;
    }

    public AgreementsModalPage clickAcceptButton(){
        this.waitForElementClickable(acceptButton, getTimeoutDefault())
                .click();
        return this;
    }
}
