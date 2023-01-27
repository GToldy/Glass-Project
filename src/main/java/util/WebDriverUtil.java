package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverUtil {

    private static WebDriverUtil webDriverUtil = null;
    private static WebDriver driver;


    private WebDriverUtil() {
    }


    public static WebDriverUtil getInstance() {
        if (webDriverUtil == null) {
            webDriverUtil = new WebDriverUtil();
        }
        return webDriverUtil;
    }


    private static void initWebDriver(String testExecutor, String nodeUrl, String browserType) throws MalformedURLException {
        if (driver == null){
            switch (browserType.toLowerCase()) {
                case "chrome":
                    if (testExecutor.equalsIgnoreCase("local")) {
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                    } else {
                        ChromeOptions chromeOptions = new ChromeOptions();
                        driver = new RemoteWebDriver(new URL(nodeUrl), chromeOptions);
                    }
                    break;
                case "firefox":
                    if (testExecutor.equalsIgnoreCase("local")) {
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                    } else {
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        driver = new RemoteWebDriver(new URL(nodeUrl), firefoxOptions);
                    }
                    break;
                default:
                    String unsupportedBrowserMessage = String.format("Cannot run tests with %s browser. Please select either Chrome or Firefox!", browserType);
                    System.out.println(unsupportedBrowserMessage);
            }
        }
        assert driver != null;
        driver.manage().window().maximize();
    }


    public static WebDriver getWebDriver() throws MalformedURLException {
        // Setup WebDriver
        initWebDriver(TestParameters.testExecutor, TestParameters.nodeURL, TestParameters.browserType);
        return driver;
    }


    public static void quitWebDriver() {
        driver.quit();
        driver = null;
    }
}
