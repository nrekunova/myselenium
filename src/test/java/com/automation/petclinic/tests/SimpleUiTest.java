package com.automation.petclinic.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SimpleUiTest {

    private WebDriver driver;

    @BeforeClass
    public void setUpDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

//    @BeforeMethod
//    public void setUp(){
//        driver = new ChromeDriver();
//    }
//    @AfterMethod
//    public void aftermeth(){
//        driver.quit();
//    }

    @AfterClass
    public void close(){
        driver.quit();
    }
    @Test
    public void firstUiTest(){
        driver.get("https://selenium.dev/");
       // driver.get("https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager/3.7.1");
//        driver.navigate().
//        driver.close();
        Assert.assertEquals(driver.getTitle(),"SeleniumHQ Browser Automation");
    }

    @Test
    public void secondUiTest(){
        driver.get("https://selenium.dev/downloads/");
       Assert.assertEquals(driver.getTitle(),"Downloads");
    }
    @Test
    public void thirdUiTest(){
        driver.get("https://selenium.dev/about/");
        Assert.assertEquals(driver.getTitle(),"About Selenium");
    }
    @Test
    public void fourthUiTest(){
        driver.get("https://selenium.dev/events/");
        Assert.assertEquals(driver.getTitle(),"Selenium Events");
    }
    @Test
    public void fifthUiTest(){
        driver.get("https://selenium.dev/ecosystem/");
        Assert.assertEquals(driver.getTitle(),"Ecosystem");
    }

    @Test
    public void seventhUiTest(){
        driver.get("https://selenium.dev/projects/");
        Assert.assertEquals(driver.getTitle(),"Selenium Projects");
    }
    @Test
    public void eightUiTest(){
        driver.get("https://selenium.dev/support/");
        Assert.assertEquals(driver.getTitle(),"Selenium Support");
    }
    @Test
    public void sixthUiTest(){
        driver.get("https://selenium.dev/blog/");
        Assert.assertEquals(driver.getTitle(),"Selenium Blog");
    }


}
