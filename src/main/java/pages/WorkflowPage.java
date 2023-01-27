package pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Util;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static util.Util.lookUpWebElementByXpath;

public class WorkflowPage extends Base {
    @FindBy(xpath = "//span[contains(@class, 'css-19r5em7') and normalize-space(text()) = 'Skip']")
    WebElement skipButton;

    @FindBy(xpath = "//tbody/tr")
    List<WebElement> transitionRows;

    @FindBy(xpath = "//div[@class='css-e6hn7k']")
    WebElement modal;


    public void clickToTransitionName(){
        Util.navigateToUrl(driver,"https://jira-expert.codecool.metastage.net/projects/ADMIN?selectedItem=com.metainf.jira.plugin:glass-project-documentation#/home/issueTypes/10002/transitions");
        WebElement skipBtn = Util.lookUpWebElementWithWait(driver,skipButton);
        skipBtn.click();
    }


    private String getFromStatus(int row){
        String path = String.format("//tbody/tr[%d]/td/div//div", row);
        if(row == 1){
            String fromStatus = driver.findElement(By.xpath(path)).getAttribute("style");
            Pattern pattern = Pattern.compile("rgb\\(\\d{1,3}.\\s\\d{1,3}.\\s\\d{1,3}\\)");
            Matcher matcher = pattern.matcher(fromStatus);
            return matcher.find() ? matcher.group() : "";
        }else{
            String fromStatus = driver.findElement(By.xpath(path)).getAttribute("class");
            List<String> status = List.of(fromStatus.split(" "));
            return status.get(1);
        }
    }


    private String getToStatus(int row){
        String path = String.format("//tbody/tr[%d]/td/span/div[contains(@class, 'glass-workflow')]", row);
        String toStatus = driver.findElement(By.xpath(path)).getAttribute("class");
        List<String> status = List.of(toStatus.split(" "));
        return status.get(1);
    }



    private String getTransitionName(int row){
        try {
            String path = String.format("//tbody/tr[%d]/td//strong", row);
            return lookUpWebElementByXpath(driver, path);
        } catch (NoSuchElementException | TimeoutException err) {
            return "";
        }
    }

    private String getTransitionType(int row){
        try {
            String path = String.format("//tbody/tr[%d]/td/span[not(descendant::*)]", row);
            return lookUpWebElementByXpath(driver, path);
        } catch (NoSuchElementException | TimeoutException err) {
            return "";
        }
    }

    private String getTransitionScreen(int row){
        try {
            String path = String.format("//tbody/tr[%d]/td//span[contains(@style, 'margin')]/parent::span", row);
            return lookUpWebElementByXpath(driver, path);
        } catch (NoSuchElementException | TimeoutException err) {
            return "";
        }
    }

    private String getPostFunction(int row){
        try {
            String path = String.format("//tbody/tr[%d]/td//span[contains(text(), 'Custom')]/span", row);
            return lookUpWebElementByXpath(driver, path);
        } catch (NoSuchElementException | TimeoutException err) {
            return "";
        }
    }

    public List<String> getAllDetails (int row){
        List<String> data = new ArrayList<>();
        data.add(getFromStatus(row));
        data.add(getTransitionName(row));
        data.add(getToStatus(row));
        data.add(getTransitionScreen(row));
        data.add(getPostFunction(row));
        return data;
    }


    public List<WebElement> getTransitionRows() {
        return transitionRows;
    }




    public WorkflowPage() throws MalformedURLException {}
}
