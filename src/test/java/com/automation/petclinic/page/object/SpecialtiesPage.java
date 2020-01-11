package com.automation.petclinic.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class SpecialtiesPage extends BasePage {

    public SpecialtiesPage(WebDriver driver){
        this.driver = driver;
    }

    public void openPage(){
        driver.get(mainUrl + "/specialties");
        Assert.assertEquals(driver.getTitle(),"SpringPetclinicAngular");
        waitFor().withMessage("Specialties page is not open!")
                .until(ExpectedConditions.textToBe(By.xpath("//h2"), "Specialties"));
    }

    private void addSpecialtyIfNotExists(String type) {
        this.openPage();
////        driver.get(mainUrl + "/specialties");
//        Assert.assertEquals(driver.getTitle(), "SpringPetclinicAngular");

        List<String> specialties = getSpecialties();
        if (!specialties.contains(type)) {
            WebElement addButton = driver.findElement(By.xpath("//button[contains(text(), ' Add ')]"));
            Assert.assertTrue(addButton.isEnabled(), "Add button should be enabled");
            addButton.click();

            WebElement name = driver.findElement(By.id("name"));
            Assert.assertTrue(name != null);
            name.sendKeys(type);
            WebElement saveButton = driver.findElement(By.xpath("//*[contains(text(), 'Save')]"));
            saveButton.click();

            specialties = getSpecialties();
            Assert.assertTrue(specialties.contains(type));
        }
    }


    private List<String> getSpecialties(){
        WebElement table = driver.findElement(By.id("specialties"));
        List<String> specialties = new ArrayList<>();
        table.findElements(By.xpath("//input[@type='text']")).forEach(we -> {
            specialties.add(we.getAttribute("ng-reflect-model"));
        });
        return specialties;
    }
}
