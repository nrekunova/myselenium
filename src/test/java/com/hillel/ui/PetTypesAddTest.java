package com.hillel.ui;

import com.automation.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PetTypesAddTest {

    private WebDriver driver;

    @BeforeClass
    public void setUpWebDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    private void addPetTypeTest() {
        driver.get(Config.MAIN_URL + "/pettypes");
        Assert.assertEquals(driver.getTitle(), "SpringPetclinicAngular");
        String testType = "testType";
        if(getPetTypes().contains(testType)){
            WebElement cell = driver.findElement(By.xpath("//input[@ng-reflect-model='" + testType + "']"));
            WebElement row = getParent(getParent(cell));
            row.findElement(By.xpath("./td/button[contains(text(), 'Delete')]")).click();
            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
            driver.get(Config.MAIN_URL + "/pettypes");
            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
            Assert.assertFalse(getPetTypes().contains(testType), "New pet type shouldn't be in the table");
        }
         WebElement addButton = driver.findElement(By.xpath("//button[contains(text(), ' Add ')]"));
         addButton.click();

         WebElement inputName = driver.findElement(By.id("name"));
            inputName.sendKeys(testType);
            WebElement saveButton = driver.findElement(By.xpath("//*[contains(text(), 'Save')]"));
            saveButton.click();

            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        driver.get(Config.MAIN_URL + "/pettypes");
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
            Assert.assertTrue(getPetTypes().contains(testType), "New pet type should appear in the table");

    }
    private List<String> getPetTypes() {
        List<String> pettypes = new ArrayList<>();
        driver.findElements(By.xpath("//table[@id='pettypes']/tbody/tr/td/input[@type='text']")).forEach(we -> {
            pettypes.add(we.getAttribute("ng-reflect-model"));
        });
        return pettypes;
    }

    private WebElement getParent(WebElement childElement){
        WebElement parentElement = childElement.findElement(By.xpath("./.."));
        return parentElement;
    }
}
