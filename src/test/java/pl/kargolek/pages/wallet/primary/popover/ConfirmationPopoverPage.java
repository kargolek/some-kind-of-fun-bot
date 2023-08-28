package pl.kargolek.pages.wallet.primary.popover;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class ConfirmationPopoverPage extends PopoverBasePage {

    @FindBy(xpath = ".//div[@id='popover-content']//button[contains(@class, 'btn-primary')]")
    private WebElement approveButton;

    @FindBy(xpath = ".//div[@id='popover-content']//button[contains(@class, 'btn-secondary')]")
    private WebElement cancelButton;

    public ConfirmationPopoverPage(WebDriver driver) {
        super(driver);
    }

    public ConfirmationPopoverPage clickCancelButton() {
        this.waitForElementClickable(this.cancelButton, Duration.ofSeconds(10))
                .click();
        return this;
    }

    public ConfirmationPopoverPage clickApproveButton() {
        this.waitForElementClickable(this.approveButton, Duration.ofSeconds(10))
                .click();
        return this;
    }
}
