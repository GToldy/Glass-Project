package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import util.WebDriverUtil;

import java.net.MalformedURLException;

public abstract class Base {

    protected WebDriver driver;

    public Base() throws MalformedURLException {
        WebDriverUtil.getInstance();
        this.driver = WebDriverUtil.getWebDriver();
        PageFactory.initElements(driver, this);
    }
}
