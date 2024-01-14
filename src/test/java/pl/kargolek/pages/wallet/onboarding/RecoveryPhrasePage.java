package pl.kargolek.pages.wallet.onboarding;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class RecoveryPhrasePage extends BasePage {

    @FindBy(xpath = ".//input[@data-testid='import-srp__srp-word-0']")
    private WebElement phraseOneInput;

    @FindBy(xpath = ".//button[@data-testid='import-srp-confirm']")
    private WebElement confirmImportButton;

    public RecoveryPhrasePage(WebDriver driver) {
        super(driver);
    }

    public CreatePasswordPage inputRecoveryPhrase(String phrase){
        this.waitForElementVisibility(phraseOneInput, Duration.ofSeconds(10));
        var words = Arrays.stream(phrase.split(" ")).toList();

        AtomicInteger index = new AtomicInteger(0);
        words.forEach(word -> {
            int currentIndex = index.getAndIncrement();
            driver.findElement(By.xpath(String.format(".//input[@data-testid='import-srp__srp-word-%s']", currentIndex)))
                    .sendKeys(word);
        });
        this.waitForElementClickable(this.confirmImportButton, Duration.ofSeconds(10)).click();
        return new CreatePasswordPage(this.driver);
    }
}
