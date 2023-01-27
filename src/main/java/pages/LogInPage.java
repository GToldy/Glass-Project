package pages;

import base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Util;

import java.net.MalformedURLException;

public class LogInPage extends Base {

    @FindBy(xpath = "//input[@id='login-form-username']")
    WebElement usernameInput;

    @FindBy(xpath = "//input[@id='login-form-password']")
    WebElement passwordInput;

    @FindBy(xpath = "//input[@id='login-form-submit']")
    WebElement loginButton;

    @FindBy(xpath = "//div[@class='aui-message aui-message-error']/child::p")
    WebElement logInErrorMessage;




    public LogInPage() throws MalformedURLException {
    }




    private void setUsernameInput(String username) {
        Util.lookUpWebElementWithWait(driver, usernameInput).sendKeys(username);
    }

    private void setPasswordInput(String password) {
        Util.lookUpWebElementWithWait(driver, passwordInput).sendKeys(password);
    }

    private void clickOnLoginButton() {
        Util.lookUpWebElementWithWait(driver, loginButton).click();
    }

    public String getErrorMessage() {
        return Util.lookUpWebElementWithWait(driver, logInErrorMessage).getText();
    }



    public void logInWithUser(String url, String username, String password) {
        Util.navigateToUrl(driver, url);
        this.setUsernameInput(username);
        this.setPasswordInput(password);
        this.clickOnLoginButton();
    }
}
