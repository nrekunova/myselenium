package com.automation.petclinic.tests;

import com.automation.petclinic.conf.Configuration;
import com.automation.petclinic.model.Owner;
import com.automation.petclinic.page.object.NewOwnerPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class AddOwnerTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver = Configuration.getInstance().getDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void saveButtonAndValidationTest() {
        NewOwnerPage page = new NewOwnerPage(driver);
        page.openPage();

        page.addOwnerButtonIsDisabled();

        page.setOneLetterToFirstNameAndCheckError();
        page.clearFirstNameAndCheckRequired();
        page.fillFirstName("Tom");

        page.setOneLetterToLastNameAndCheckError();
        page.clearLastNameAndCheckRequired();
        page.fillLastName("Ford");

        page.setLetterToTelephoneAndCheckError();
        page.clearTelephoneAndCheckRequired();
        page.fillTelephone("12345");

        page.fillCity("t");
        page.clearCityAndCheckRequired();
        page.fillCity("New York");

        page.fillAddress("t");
        page.clearAddressAndCheckRequired();
        page.fillAddress("5th Avenue, 4");

        page.addOwnerButtonIsEnabled();
    }

    @Test
    public void addOwner() {
        NewOwnerPage page = new NewOwnerPage(driver);
        page.openPage();

        Owner owner = new Owner();
        owner.setFirstName("testFirstName");
        owner.setLastName("testLastName");
        owner.setAddress("testAddress");
        owner.setCity("testCity");
        owner.setTelephone("123456");

        page.fillFirstName(owner.getFirstName());
        page.fillLastName(owner.getLastName());
        page.fillAddress(owner.getAddress());
        page.fillCity(owner.getCity());
        page.fillTelephone(owner.getTelephone());

        page.clickAddOwnerAndWaitForOwnersPage(owner);
    }

}
