package pl.kargolek.pages.wallet.primary.popover;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;

import java.time.Duration;

@Slf4j
public class PopoverBasePage extends BasePage {

    @FindBy(xpath = ".//button[@data-testid='popover-close']")
    private WebElement closeButton;

    @FindBy(css = ".popover-wrap")
    private WebElement popoverContainer;

    public PopoverBasePage(WebDriver driver) {
        super(driver);
    }

    public PopoverBasePage clickCloseButton() throws InterruptedException {
        Thread.sleep(2000);
        try {
            var element = this.waitForElementClickable(this.closeButton, Duration.ofSeconds(20));
            element.click();
        } catch (StaleElementReferenceException e) {
            this.waitForElementClickable(this.closeButton, Duration.ofSeconds(20))
                    .click();
        }
        return this;
    }

    public PopoverBasePage clickCloseIfExist() throws InterruptedException {
        Thread.sleep(2000);
        WebElement element;
        try {
            element = this.waitForElementVisibility(this.closeButton,
                    Duration.ofSeconds(5));
            element.click();
        } catch (TimeoutException e) {
            log.info("Element not exist. Click action aborted.");
        }
        return this;
    }
}
