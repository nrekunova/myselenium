package com.hillel.ui;

import com.automation.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PetclinicAddOwnerTest {
    private WebDriver driver;

    @BeforeClass
    public void setUpWebDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void saveButtonAndValidationTest(){
        driver.get(Config.MAIN_URL + "/owners/add");
        Assert.assertEquals(driver.getTitle(),"SpringPetclinicAngular");

        WebElement button = driver.findElement(By.xpath("//*[contains(text(), 'Add Owner')]"));
        Assert.assertFalse(button.isEnabled(), "Save button should be disabled");

        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.clear();
        firstName.sendKeys("t");
        WebElement error = getParent(firstName).findElement(By.className("help-block"));
        Assert.assertEquals(error.getText(), "First name must be at least 2 characters long");

        firstName.sendKeys(Keys.BACK_SPACE);
        error = getParent(firstName).findElement(By.className("help-block"));
        Assert.assertEquals(error.getText(), "First name is required");
        firstName.sendKeys("Tom");


        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.clear();
        lastName.sendKeys("t");
        error = getParent(lastName).findElement(By.className("help-block"));
        Assert.assertEquals(error.getText(), "Last name must be at least 2 characters long");

        lastName.sendKeys(Keys.BACK_SPACE);
        error = getParent(lastName).findElement(By.className("help-block"));
        Assert.assertEquals(error.getText(), "Last name is required");
        lastName.sendKeys("Ford");

        WebElement telephone = driver.findElement(By.id("telephone"));
        telephone.clear();
        telephone.sendKeys("t");
        error = getParent(telephone).findElement(By.className("help-block"));
        Assert.assertEquals(error.getText(), "Phone number only accept digits");

        telephone.sendKeys(Keys.BACK_SPACE);
        error = getParent(telephone).findElement(By.className("help-block"));
        Assert.assertEquals(error.getText(), "Phone number is required");
        telephone.sendKeys("1234567890");

        WebElement city = driver.findElement(By.id("city"));
        city.clear();
        city.sendKeys("t");

        city.sendKeys(Keys.BACK_SPACE);
        error = getParent(city).findElement(By.className("help-block"));
        Assert.assertEquals(error.getText(), "City is required");
        city.sendKeys("New York");

        WebElement address = driver.findElement(By.id("address"));
        address.clear();
        address.sendKeys("t");

        address.sendKeys(Keys.BACK_SPACE);
        error = getParent(address).findElement(By.className("help-block"));
        Assert.assertEquals(error.getText(), "Address is required");
        address.sendKeys("5th Avenue, 4");

        button = driver.findElement(By.xpath("//*[contains(text(), 'Add Owner')]"));
        Assert.assertTrue(button.isEnabled(), "Save button should be enabled");
    }

    @Test
    public void addOwner(){
        driver.get(Config.MAIN_URL + "/owners/add");
        Assert.assertEquals(driver.getTitle(),"SpringPetclinicAngular");
        Owner owner = new Owner();
        owner.setFirstName("testFirstName");
        owner.setLastName("testLastName");
        owner.setAddress("testAddress");
        owner.setCity("testCity");
        owner.setTelephone("123456");

        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.clear();
        firstName.sendKeys(owner.getFirstName());

        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.clear();
        lastName.sendKeys(owner.getLastName());

        WebElement address = driver.findElement(By.id("address"));
        address.clear();
        address.sendKeys(owner.getAddress());

        WebElement city = driver.findElement(By.id("city"));
        city.clear();
        city.sendKeys(owner.getCity());

        WebElement telephone = driver.findElement(By.id("telephone"));
        telephone.clear();
        telephone.sendKeys(owner.getTelephone());

        WebElement button = driver.findElement(By.xpath("//*[contains(text(), 'Add Owner')]"));
        button.click();
        WebDriverWait wdw = new WebDriverWait(driver, 5);
        wdw.until(ExpectedConditions.urlToBe(Config.MAIN_URL + "/owners"));

        Assert.assertEquals(driver.getCurrentUrl(), Config.MAIN_URL + "/owners");

        Assert.assertTrue(getOwnersList().contains(owner), "New test owner should be present in the table");


    }

    private WebElement getParent(WebElement childElement){
        WebElement parentElement = childElement.findElement(By.xpath("./.."));
        return parentElement;
    }

    public List<Owner> getOwnersList() {
        List<Owner> owners = new ArrayList<>();
        WebElement ownersTable = driver.findElement(By.xpath("//*[@class='table-responsive']"));

        List<WebElement> ownersList = ownersTable.findElements(By.xpath(".//tbody/tr"));
        for (WebElement userRow : ownersList) {
            owners.add(createOwner(userRow));
        }

        return owners;
    }
    private Owner createOwner(WebElement userRow) {
        Owner owner = new Owner();
        String fullName = userRow.findElement(By.xpath("./td/a")).getText();
        String[] fullNameArray = fullName.split(" ");
        if (fullNameArray.length > 1) {
            owner.setFirstName(fullNameArray[0]);
            owner.setLastName(fullNameArray[1]);
        } else {
            owner.setFirstName(fullNameArray[0]);
        }
        owner.setAddress(userRow.findElement(By.xpath("./td[2]")).getText());
        owner.setCity(userRow.findElement(By.xpath("./td[3]")).getText());
        owner.setTelephone(userRow.findElement(By.xpath("./td[4]")).getText());

        String pets = userRow.findElement(By.xpath("./td[5]")).getText();
        if(!pets.isEmpty()) {
            owner.setPets(pets);
        }

        return owner;
    }
//    http://139.59.149.247:8000/petclinic/owners/add
}
