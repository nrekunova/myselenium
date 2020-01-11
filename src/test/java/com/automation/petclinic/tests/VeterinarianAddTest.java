package com.automation.petclinic.tests;

import com.automation.petclinic.conf.Configuration;
import com.automation.petclinic.model.Veterinarian;
import com.automation.petclinic.page.object.VeterinarianAddPage;
import com.automation.petclinic.page.object.VeterinariansPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class VeterinarianAddTest {

//    private static final String mainUrl = Configuration.getInstance().getMainUrl();

    private WebDriver driver;

//    @BeforeClass
//    public void setUpWebDriver(){
//        WebDriverManager.chromedriver().setup();
//    }

    @BeforeMethod
    public void setUp(){
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver = Configuration.getInstance().getDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
//    @Test
//    private void test(){
//        driver.get(mainUrl + "/vets");
//        getVeterinarianList().forEach(v -> {
//            System.out.println(v);
//        });
//    }

    @Test
    private void addVeterinarianTest() {
        Veterinarian veterinarian = new Veterinarian();
        veterinarian.setFirstName("testFirstName");
        veterinarian.setLastName("testLastName");
        veterinarian.setType("surgery");
//        addSpecialtyIfNotExists(veterinarian.getType());
//
//        driver.get(mainUrl + "/vets/add");
//        Assert.assertEquals(driver.getTitle(),"SpringPetclinicAngular");
        VeterinarianAddPage page = new VeterinarianAddPage(driver);
        page.openPage();

        page.setOneLetterToFirstNameAndCheckError();
        page.clearFirstNameAndCheckRequired();
        page.fillFirstName(veterinarian.getFirstName());

        page.setOneLetterToLastNameAndCheckError();
        page.clearLastNameAndCheckRequired();
        page.fillLastName(veterinarian.getLastName());

        page.selectSpecialty(veterinarian.getType());

        page.clickSave();

        VeterinariansPage vpage = new VeterinariansPage(driver);
        vpage.openPage();
        vpage.tableContainsVeterinarian(veterinarian);

//        WebElement firstName = driver.findElement(By.id("firstName"));
//        firstName.clear();
//        firstName.sendKeys("t");
//        WebElement error = getParent(firstName).findElement(By.className("help-block"));
//        Assert.assertEquals(error.getText(), "First name must be at least 2 characters long");
//
//        firstName.sendKeys(Keys.BACK_SPACE);
//        error = getParent(firstName).findElement(By.className("help-block"));
//        Assert.assertEquals(error.getText(), "First name is required");
//        firstName.sendKeys(veterinarian.getFirstName());

//
//        WebElement lastName = driver.findElement(By.id("lastName"));
//        lastName.clear();
//        lastName.sendKeys("t");
//        WebElement error = getParent(lastName).findElement(By.className("help-block"));
//        Assert.assertEquals(error.getText(), "Last name must be at least 2 characters long");
//        lastName.sendKeys(Keys.BACK_SPACE);
//        error = getParent(lastName).findElement(By.className("help-block"));
//        Assert.assertEquals(error.getText(), "Last name is required");
//        lastName.sendKeys(veterinarian.getLastName());

//
//        Select drpType = new Select(driver.findElement(By.id("specialties")));
//        drpType.selectByVisibleText(veterinarian.getType());



//        WebElement saveVetButton = driver.findElement(By.xpath("//button[contains(text(), 'Save Vet')]"));
//        saveVetButton.click();

//        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
//        Assert.assertEquals(driver.getCurrentUrl(), mainUrl + "/vets");

//        Assert.assertTrue(getVeterinarianList().contains(veterinarian), "New test veterinarian should be present in the table");

    }
//
//    private WebElement getParent(WebElement childElement){
//        WebElement parentElement = childElement.findElement(By.xpath("./.."));
//        return parentElement;
//    }


//    private void addSpecialtyIfNotExists(String type){
////        driver.get(mainUrl + "/specialties");
//        Assert.assertEquals(driver.getTitle(),"SpringPetclinicAngular");
//
//        List<String> specialties = getSpecialties();
//        if (!specialties.contains(type)) {
//            WebElement addButton = driver.findElement(By.xpath("//button[contains(text(), ' Add ')]"));
//            Assert.assertTrue(addButton.isEnabled(), "Add button should be enabled");
//            addButton.click();
//
//            WebElement name = driver.findElement(By.id("name"));
//            Assert.assertTrue(name!= null);
//            name.sendKeys(type);
//            WebElement saveButton = driver.findElement(By.xpath("//*[contains(text(), 'Save')]"));
//            saveButton.click();
//
//            specialties = getSpecialties();
//            Assert.assertTrue(specialties.contains(type));
//        }
//    }
//
//    private List<String> getSpecialties(){
//        WebElement table = driver.findElement(By.id("specialties"));
//        List<String> specialties = new ArrayList<>();
//        table.findElements(By.xpath("//input[@type='text']")).forEach(we -> {
//            specialties.add(we.getAttribute("ng-reflect-model"));
//        });
//        return specialties;
//    }

//    public List<Veterinarian> getVeterinarianList() {
//        List<Veterinarian> veterinarians = new ArrayList<>();
//        WebElement veterinarianTable = driver.findElement(By.xpath("//*[@class='table table-striped']"));
//
//        List<WebElement> veterinarianList = veterinarianTable.findElements(By.xpath(".//tbody/tr"));
//        for (WebElement userRow : veterinarianList) {
//            veterinarians.add(createVeterinarian(userRow));
//        }
//
//        return veterinarians;
//    }

//    private Veterinarian createVeterinarian(WebElement userRow) {
//        Veterinarian veterinarian = new Veterinarian();
//        String fullName = userRow.findElement(By.xpath("./td[1]")).getText();
//        String[] fullNameArray = fullName.split(" ");
//        if (fullNameArray.length > 1) {
//            veterinarian.setFirstName(fullNameArray[0]);
//            veterinarian.setLastName(fullNameArray[1]);
//        } else {
//            veterinarian.setFirstName(fullNameArray[0]);
//        }
//        String type = userRow.findElement(By.xpath("./td[2]")).getText();
//        if(!type.isEmpty()) {
//            veterinarian.setType(type);
//        }
//        return veterinarian;
//    }
}
