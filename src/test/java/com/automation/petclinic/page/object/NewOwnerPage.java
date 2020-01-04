package com.automation.petclinic.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewOwnerPage {
    private WebDriver driver;
    public  NewOwnerPage(WebDriver driver){
        this.driver = driver;
    }
    public void setFirstName(String lastName){
        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.clear();
    }
}
