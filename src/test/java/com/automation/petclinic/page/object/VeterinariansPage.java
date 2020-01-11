package com.automation.petclinic.page.object;

import com.automation.petclinic.model.Veterinarian;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class VeterinariansPage extends BasePage {

    public VeterinariansPage(WebDriver driver){
        this.driver = driver;
    }

    public void openPage(){
        driver.get(mainUrl + "/vets");
        Assert.assertEquals(driver.getTitle(),"SpringPetclinicAngular");
        waitFor().withMessage("Veterinarians page is not open!")
                .until(ExpectedConditions.textToBe(By.xpath("//h2"), "Veterinarians"));
    }

    public void tableContainsVeterinarian (Veterinarian veterinarian){
        Assert.assertTrue(getVeterinarianList().contains(veterinarian), "New test veterinarian should be present in the table");

    }
    public List<Veterinarian> getVeterinarianList() {
        List<Veterinarian> veterinarians = new ArrayList<>();
        WebElement veterinarianTable = driver.findElement(By.xpath("//*[@class='table table-striped']"));

        List<WebElement> veterinarianList = veterinarianTable.findElements(By.xpath(".//tbody/tr"));
        for (WebElement userRow : veterinarianList) {
            veterinarians.add(createVeterinarian(userRow));
        }

        return veterinarians;
    }
    private Veterinarian createVeterinarian(WebElement userRow) {
        Veterinarian veterinarian = new Veterinarian();
        String fullName = userRow.findElement(By.xpath("./td[1]")).getText();
        String[] fullNameArray = fullName.split(" ");
        if (fullNameArray.length > 1) {
            veterinarian.setFirstName(fullNameArray[0]);
            veterinarian.setLastName(fullNameArray[1]);
        } else {
            veterinarian.setFirstName(fullNameArray[0]);
        }
        String type = userRow.findElement(By.xpath("./td[2]")).getText();
        if(!type.isEmpty()) {
            veterinarian.setType(type);
        }
        return veterinarian;
    }

}
