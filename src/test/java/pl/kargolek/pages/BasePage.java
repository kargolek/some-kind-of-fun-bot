package pl.kargolek.pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Karol Kuta-Orlowicz
 */
@Getter
@Slf4j
public class BasePage {

    public final WebDriver driver;
    public final Actions action;
    public final FluentWait<WebDriver> fluentWait;

    final Duration timeoutDefault = Duration.ofSeconds(15);
    final Duration timeout5Sec = Duration.ofSeconds(5);
    final Duration timeout1Sec = Duration.ofSeconds(1);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
        this.fluentWait = new FluentWait<>(driver);

        PageFactory.initElements(driver, this);
    }

    public WebElement waitForElementVisibility(WebElement element, Duration timeout) {
        return new WebDriverWait(this.driver, timeout).until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementVisibility(By by, Duration timeout) {
        return new WebDriverWait(this.driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by, Duration timeout) {
        return new WebDriverWait(this.driver, timeout).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public Boolean waitForElementInvisibility(WebElement element, Duration timeout) {
        return new WebDriverWait(this.driver, timeout).until(ExpectedConditions.invisibilityOf(element));
    }

    public List<WebElement> waitForElementAllVisibility(WebElement element, Duration timeout) {
        return new WebDriverWait(this.driver, timeout).until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public List<WebElement> waitForElementsVisibility(List<WebElement> elements, Duration timeout) {
        return new WebDriverWait(this.driver, timeout).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public List<WebElement> waitForElementsVisibilityIgnoreTimeout(List<WebElement> elements, Duration timeout) {
        List<WebElement> elementsArray = new ArrayList<>();
        try {
            elementsArray = new WebDriverWait(this.driver, timeout)
                    .until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch(TimeoutException e){
            log.info("Timeout occurs, ignoring it. Element: " + elements);
        }
        return elementsArray;
    }

    public List<WebElement> waitForElementsVisibilityIgnoreTimeout(WebElement element, Duration timeout) {
        List<WebElement> elementsArray = new ArrayList<>();
        try {
            elementsArray = new WebDriverWait(this.driver, timeout)
                    .until(ExpectedConditions.visibilityOfAllElements(element));
        } catch(TimeoutException e){
            log.info("Timeout occurs, ignoring it. Element: " + element);
        }
        return elementsArray;
    }

    public List<WebElement> waitForElementsVisibilityIgnoreTimeout(By locator, Duration timeout) {
        List<WebElement> elementsArray = new ArrayList<>();
        try {
            elementsArray = new WebDriverWait(this.driver, timeout)
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch(TimeoutException e){
            log.info("Timeout occurs, ignoring it. Element: " + locator);
        }
        return elementsArray;
    }

    public boolean isWebElementVisible(WebElement element, Duration timeout){
        try {
            new WebDriverWait(this.driver, timeout).until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch(TimeoutException e){
            log.info("Timeout occurs, ignoring it. Element: " + element);
            return false;
        }
    }

    public List<WebElement> waitForElementAllVisibility(By locator, Duration timeout) {
        return new WebDriverWait(this.driver, timeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public WebElement waitForElementVisibility(WebElement element, Duration timeout, Duration interval) {
        return this.fluentWait.withTimeout(timeout)
                .pollingEvery(interval)
                .until(ExpectedConditions.visibilityOf(element));
    }

    @SafeVarargs
    public final WebElement waitForElementVisibility(WebElement element, Duration timeout, Duration interval, Class<? extends Throwable>... clazz) {
        return this.fluentWait.withTimeout(timeout)
                .pollingEvery(interval)
                .ignoreAll(Arrays.asList(clazz))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public boolean waitForElementTextToChange(WebElement element, String text, Duration timeout) {
        return new WebDriverWait(this.driver, timeout).until(
                ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, text)));
    }

    public WebElement waitForElementClickable(WebElement element, Duration timeout) {
        return new WebDriverWait(this.driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementClickable(By by, Duration timeout) {
        return new WebDriverWait(this.driver, timeout).until(ExpectedConditions.elementToBeClickable(by));
    }

    public boolean waitForContainsURL(String partURL, Duration timeout) {
        return new WebDriverWait(driver, timeout).until(ExpectedConditions.urlContains(partURL));
    }

    public WebElement clickElement(WebElement element, Duration timeout) {
        new WebDriverWait(this.driver, timeout).until(ExpectedConditions.elementToBeClickable(element)).click();
        return element;
    }

    public String getTextElement(WebElement element) {
        return element.getText();
    }

}
