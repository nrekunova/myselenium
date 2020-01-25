package com.automation.petclinic.restAssured;

import com.automation.petclinic.conf.Configuration;
import com.automation.petclinic.model.Owner;
import com.automation.petclinic.model.Pet;
import com.automation.petclinic.model.PetType;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
@Epic("Petclinic")
@Feature("Pet")

public class PetTest2 {

    private Pet pet;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = Configuration.getInstance().getApiUrl();
        RestAssured.port = Configuration.getInstance().getApiPort();
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }

    @BeforeMethod
    public void createPet() {
        pet = postPetTestWithObject();
    }

    @AfterMethod
    public void deletePet() {
        deletePetByIdTest(pet.getId());
    }

    @Test
    public void getPetByIdTest() {
        RestAssured.given()
                .get("/pets/{id}", pet.getId())
                .then()
                .statusCode(200)
                .body("id", equalTo(pet.getId()))
                .body("name", equalTo(pet.getName()))
                .body("birthDate", equalTo(pet.getBirthDate()))
                .body("owner.id", equalTo(pet.getOwner().getId()))
                .body("type.id", equalTo(pet.getType().getId()))
                .log().all();
    }

    private Owner getOwnerById(int id){
        return RestAssured.given()
                .get("/owners/{id}", id)
                .then()
                .statusCode(200)
                .log().all()
                .extract().body()
                .as(Owner.class);
    }

    private PetType getPetTypeById(int id){
        return RestAssured.given()
                .get("/pettypes/{id}", id)
                .then()
                .statusCode(200)
                .log().all()
                .extract().body()
                .as(PetType.class);

    }
    private Pet postPetTestWithObject() {
        Pet pet = new Pet();
        pet.setName("testNameApi");
        pet.setBirthDate("2018/09/07");
        pet.setType(getPetTypeById(1));
        pet.setOwner(getOwnerById(1));
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(pet)
                .log().all()
                .post("/pets")
                .then()
                .log().all()
                .statusCode(201)
                .extract().body()
                .as(Pet.class);
    }

    public void deletePetByIdTest(int id) {
        RestAssured.given()
                .log().all()
                .delete("/pets/{id}", id)
                .then()
                .statusCode(204);
    }

    private void putPetTestWithObject() {
        pet.setName("Leo");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(pet)
                .put("/pets/{id}", pet.getId())
                .then()
                .statusCode(204);
    }

    @Test
    public void getPetByIdAfterPutTest() {
        putPetTestWithObject();
        getPetByIdTest();
    }
}
