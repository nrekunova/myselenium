package com.automation.petclinic.page.object;

import com.automation.petclinic.conf.Configuration;
import com.automation.petclinic.model.Owner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class NewOwnerPage extends BasePage {


    public  NewOwnerPage(WebDriver driver){
        this.driver = driver;
    }

    public void openPage(){
        driver.get(mainUrl + "/owners/add");
        Assert.assertEquals(driver.getTitle(),"SpringPetclinicAngular");
        waitFor().withMessage("New Owner page is not open!")
                .until(ExpectedConditions.textToBe(By.xpath("//h2"), "New Owner"));
    }

    public void addOwnerButtonIsDisabled (){
        WebElement button = driver.findElement(By.xpath("//*[contains(text(), 'Add Owner')]"));
        Assert.assertFalse(button.isEnabled(), "Add Owner button should be disabled");
    }

    private WebElement getFirstNameField(){
        return driver.findElement(By.id("firstName"));
    }

//    private void setInvalidValueAndCheckError(WebElement field, String errorText, CharSequence ... c) {
//        field.sendKeys(c);
//        WebElement error = getParent(field).findElement(By.className("help-block"));
//        Assert.assertEquals(error.getText(), errorText);
//    }

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

    private WebElement getTelephoneField(){
        return driver.findElement(By.id("telephone"));
    }

    public void setLetterToTelephoneAndCheckError (){
        WebElement field = getTelephoneField();
        field.clear();
        setInvalidValueAndCheckError(field, "Phone number only accept digits", "t");
    }

    public void clearTelephoneAndCheckRequired() {
        WebElement field = getTelephoneField();
        setInvalidValueAndCheckError(field, "Phone number is required", Keys.BACK_SPACE );
    }

    public void fillTelephone(String text) {
        WebElement field = getTelephoneField();
        field.clear();
        field.sendKeys(text);
    }

    private WebElement getCityField(){
        return driver.findElement(By.id("city"));
    }


    public void clearCityAndCheckRequired() {
        WebElement field = getCityField();
        setInvalidValueAndCheckError(field, "City is required", Keys.BACK_SPACE );
    }

    public void fillCity(String text) {
        WebElement field = getCityField();
        field.clear();
        field.sendKeys(text);
    }

    private WebElement getAddressField(){
        return driver.findElement(By.id("address"));
    }


    public void clearAddressAndCheckRequired() {
        WebElement field = getAddressField();
        setInvalidValueAndCheckError(field, "Address is required", Keys.BACK_SPACE );
    }

    public void fillAddress(String text) {
        WebElement field = getAddressField();
        field.clear();
        field.sendKeys(text);
    }

    public void addOwnerButtonIsEnabled (){
        WebElement button = driver.findElement(By.xpath("//*[contains(text(), 'Add Owner')]"));
        Assert.assertTrue(button.isEnabled(), "Add Owner button should be enabled");
    }



    public void clickAddOwnerAndWaitForOwnersPage(Owner owner){
        WebElement button = driver.findElement(By.xpath("//*[contains(text(), 'Add Owner')]"));
        button.click();
        WebDriverWait wdw = new WebDriverWait(driver, 5);
        wdw.until(ExpectedConditions.urlToBe(mainUrl + "/owners"));

        Assert.assertEquals(driver.getCurrentUrl(), mainUrl + "/owners");

        Assert.assertTrue(getOwnersList().contains(owner), "New test owner should be present in the table");
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
        if (!pets.isEmpty()) {
            owner.setPets(pets);
        }

        return owner;
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
}
