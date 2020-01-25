package com.automation.petclinic.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PetTypesPage extends BasePage {

    public  PetTypesPage(WebDriver driver){
        this.driver = driver;
    }
    @Step("Open page")
    public void openPage(){
        driver.get(mainUrl + "/pettypes");
        Assert.assertEquals(driver.getTitle(),"SpringPetclinicAngular");
        waitFor().withMessage("Pet Types page is not open!")
                .until(ExpectedConditions.textToBe(By.xpath("//h2"), "Pet Types"));
    }
    @Step("Delete pet type if it's existing")
    public void deleteIfExist(String petType){
        if(getPetTypes().contains(petType)){
            WebElement cell = driver.findElement(By.xpath("//input[@ng-reflect-model='" + petType + "']"));
            WebElement row = getParent(getParent(cell));
            row.findElement(By.xpath("./td/button[contains(text(), 'Delete')]")).click();
            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
            driver.get(mainUrl + "/pettypes");
            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
            Assert.assertFalse(getPetTypes().contains(petType), "New pet type shouldn't be in the table");
        }
    }

    private List<String> getPetTypes() {
        List<String> pettypes = new ArrayList<>();
        driver.findElements(By.xpath("//table[@id='pettypes']/tbody/tr/td/input[@type='text']")).forEach(we -> {
            pettypes.add(we.getAttribute("ng-reflect-model"));
        });
        return pettypes;
    }
    @Step("Click 'Add' button to add pet type")
    public void clickAddButton() {
        WebElement addButton = driver.findElement(By.xpath("//button[contains(text(), ' Add ')]"));
        addButton.click();
    }
    @Step("Fill pet type's neme")
    public void fillName(String petType) {
        WebElement inputName = driver.findElement(By.id("name"));
        inputName.sendKeys(petType);
    }
    @Step("Click 'Save' button")
    public void clickSaveButton (){
        WebElement saveButton = driver.findElement(By.xpath("//*[contains(text(), 'Save')]"));
        saveButton.click();
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
    }

    public void tableContainsPetType(String petType) {
        Assert.assertTrue(getPetTypes().contains(petType), "New pet type should appear in the table");

    }



}
