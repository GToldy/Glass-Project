package util;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Util {

    public static WebElement lookUpWebElementWithWait(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofMillis(TestParameters.timeout)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static String lookUpWebElementByXpath(WebDriver driver, String path) {
        return new WebDriverWait(driver, Duration.ofMillis(TestParameters.timeout)).until(ExpectedConditions.elementToBeClickable(By.xpath(path))).getAttribute("innerText");
    }

    public static void navigateToUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public static void acceptAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}
