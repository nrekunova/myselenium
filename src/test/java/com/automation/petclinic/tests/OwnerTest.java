//package com.hillel.ui;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.concurrent.TimeUnit;
//
//public class OwnerTest {
//    private WebDriver driver;
//
//
//    @BeforeMethod
//    public void setUp(){
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        driver.quit();
//    }
//
//    @Test
//    public void addOwnertest(){
//        driver.get("http://139.59.149.247:8000/petclinic/owners/add");
//        WebElement ownersMenuItem = driver.findElement(By.xpath("//a[@class= \"dropdown-togle\"]text())='Owners')"));
////               ownersMenuItem.click();
////       assertTrue(ownersTable.isDisplayed);
////
////        List<WebElement> ownersList = ownersTable.findElement(By.xpath(".//tbody/tr"));
////        assertFalse(ownersList));
////        WebElement userRow = ownersList.get(0));
//
//         }
//
//    }
//
