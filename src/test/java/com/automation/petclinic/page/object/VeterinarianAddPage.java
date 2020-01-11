package com.automation.petclinic.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class VeterinarianAddPage extends BasePage {

    public  VeterinarianAddPage(WebDriver driver){
        this.driver = driver;
    }

    public void openPage(){
        driver.get(mainUrl + "/vets/add");
        Assert.assertEquals(driver.getTitle(),"SpringPetclinicAngular");
        waitFor().withMessage("New Veterinarian page is not open!")
                .until(ExpectedConditions.textToBe(By.xpath("//h2"), "New Veterinarian"));
    }

    private WebElement getFirstNameField(){
        return driver.findElement(By.id("firstName"));
    }

    public void setOneLetterToFirstNameAndCheckError (){
        WebElement field = getFirstNameField();
        field.clear();
        setInvalidValueAndCheckError(field, "First name must be at least 2 characters long", "t");
    }

    public void clearFirstNameAndCheckRequired() {
        WebElement field = getFirstNameField();
        setInvalidValueAndCheckError(field, "First name is required", Keys.BACK_SPACE );
    }

    public void fillFirstName(String name) {
        WebElement field = getFirstNameField();
        field.clear();
        field.sendKeys(name);
    }

    private WebElement getLastNameField(){
        return driver.findElement(By.id("lastName"));
    }

    public void setOneLetterToLastNameAndCheckError (){
        WebElement field = getLastNameField();
        field.clear();
        setInvalidValueAndCheckError(field, "Last name must be at least 2 characters long", "t");
    }

    public void clearLastNameAndCheckRequired() {
        WebElement field = getLastNameField();
        setInvalidValueAndCheckError(field, "Last name is required", Keys.BACK_SPACE );
    }

    public void fillLastName(String name) {
        WebElement field = getLastNameField();
        field.clear();
        field.sendKeys(name);
    }

    public void selectSpecialty(String specialty){

        Select selection = new Select(driver.findElement(By.id("specialties")));
        selection.selectByVisibleText(specialty);

    }

    public void clickSave () {

        WebElement saveVetButton = driver.findElement(By.xpath("//button[contains(text(), 'Save Vet')]"));
        saveVetButton.click();
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
    }
}
