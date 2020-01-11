package com.automation.petclinic.tests;

import com.automation.petclinic.conf.Configuration;
import com.automation.petclinic.page.object.PetTypesPage;
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


//    private static final String mainUrl = Configuration.getInstance().getMainUrl();
    
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        driver = Configuration.getInstance().getDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    private void addPetTypeTest() {
//        driver.get(mainUrl + "/pettypes");
//        Assert.assertEquals(driver.getTitle(), "SpringPetclinicAngular");
        PetTypesPage page = new PetTypesPage(driver);
        page.openPage();

        String testType = "testType";
        page.deleteIfExist(testType);
//        if(getPetTypes().contains(testType)){
//            WebElement cell = driver.findElement(By.xpath("//input[@ng-reflect-model='" + testType + "']"));
//            WebElement row = getParent(getParent(cell));
//            row.findElement(By.xpath("./td/button[contains(text(), 'Delete')]")).click();
//            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
//            driver.get(mainUrl + "/pettypes");
//            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
//            Assert.assertFalse(getPetTypes().contains(testType), "New pet type shouldn't be in the table");
//        }

        page.clickAddButton();
//         WebElement addButton = driver.findElement(By.xpath("//button[contains(text(), ' Add ')]"));
//         addButton.click();

        page.fillName(testType);
//         WebElement inputName = driver.findElement(By.id("name"));
//            inputName.sendKeys(testType);

        page.clickSaveButton();
//            WebElement saveButton = driver.findElement(By.xpath("//*[contains(text(), 'Save')]"));
//            saveButton.click();

//            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
//        driver.get(mainUrl + "/pettypes");
//        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        page.openPage();
        page.tableContainsPetType(testType);
//            Assert.assertTrue(getPetTypes().contains(testType), "New pet type should appear in the table");

    }
//    private List<String> getPetTypes() {
//        List<String> pettypes = new ArrayList<>();
//        driver.findElements(By.xpath("//table[@id='pettypes']/tbody/tr/td/input[@type='text']")).forEach(we -> {
//            pettypes.add(we.getAttribute("ng-reflect-model"));
//        });
//        return pettypes;
//    }

//    private WebElement getParent(WebElement childElement){
//        WebElement parentElement = childElement.findElement(By.xpath("./.."));
//        return parentElement;
//    }
}
