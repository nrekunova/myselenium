package com.automation.petclinic.page.object;

import com.automation.petclinic.conf.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {

    protected final String mainUrl = Configuration.getInstance().getMainUrl();
    protected WebDriver driver;

    protected WebElement getParent(WebElement childElement){
        WebElement parentElement = childElement.findElement(By.xpath("./.."));
        return parentElement;
    }

    protected WebDriverWait waitFor() {
        return new WebDriverWait(driver, 4);
    }

    protected void setInvalidValueAndCheckError(WebElement field, String errorText, CharSequence ... c) {
        field.sendKeys(c);
        WebElement error = getParent(field).findElement(By.className("help-block"));
        Assert.assertEquals(error.getText(), errorText);
    }
}
