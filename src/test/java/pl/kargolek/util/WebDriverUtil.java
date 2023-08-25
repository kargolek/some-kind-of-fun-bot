package pl.kargolek.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pl.kargolek.util.exception.NoSuchWindowForOpenException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author Karol Kuta-Orlowicz
 */
public class WebDriverUtil {

    /**
     * Function switch to the next window or tab even if more than two exist
     * Need to provide parent window (current window) name
     */
    public static void switchToNextTab(WebDriver driver, String currentWindow) {
        var windowExplorer = driver.getWindowHandles().stream()
                .filter(window -> !window.equals(currentWindow))
                .findFirst()
                .orElseThrow(() -> new NoSuchWindowForOpenException(currentWindow));
        driver.switchTo().window(windowExplorer);
    }

    public static void switchToNextTab(WebDriver driver, String urlContains, long timeoutMillis) {
        var startTime = System.currentTimeMillis();
        while (true) {
            for (String window : driver.getWindowHandles()) {
                driver.switchTo().window(window);
                if (driver.getCurrentUrl().contains(urlContains)) {
                    return;
                }
            }
            if (System.currentTimeMillis() - startTime > timeoutMillis)
                throw new RuntimeException("Unable to find window with URL which contains: " + urlContains);
        }
    }

    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public static void navigateForward(WebDriver driver) {
        driver.navigate().forward();
    }

    public static void navigateBack(WebDriver driver) {
        driver.navigate().back();
    }

    public static void takeScreenshot(WebDriver driver){
        var imgElement = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        System.out.println(imgElement.getPath());
        try {
            FileUtils.copyFile(imgElement, new File("screenshots/", screenshotName()));
        } catch (IOException e) {
            throw new RuntimeException("Unable to copy element image to visual folder");
        }
    }

    private static String screenshotName(){
        var dateTime = LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME)
                .replace("-","")
                .replace(":","")
                .replace(".", "");
        return String.format("screenshot%s.png", dateTime);
    }
}
