package com.automation.petclinic.restAssured;

import com.automation.petclinic.conf.Configuration;
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

@Epic("Petclinic")
@Feature("Pet Types")

public class PetTypeTest2 {

    private PetType petType;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = Configuration.getInstance().getApiUrl();
        RestAssured.port = Configuration.getInstance().getApiPort();
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }

    @BeforeMethod
    public void createPet() {
        petType = postPetTypeTestWithObject();
    }

    @AfterMethod
    public void deletePet() {
        deletePetTypeByIdTest(petType.getId());
    }

    @Test(description = "Get pet type by Id test")
    public void getPetTypeByIdTest() {
        RestAssured.given()
                .get("/pettypes/{id}", petType.getId())
                .then()
                .statusCode(200)
                .body("id", equalTo(petType.getId()))
                .body("name", equalTo(petType.getName()))
                .log().all();
    }


    private PetType postPetTypeTestWithObject() {
        PetType squirrelPet = new PetType();
        squirrelPet.setName("squirrel");
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(squirrelPet)
                .post("/pettypes")
                .then()
                .statusCode(201)
                .extract().body()
                .as(PetType.class);
    }

    public void deletePetTypeByIdTest(int petId) {
        RestAssured.given()
                .log().all()
                .delete("/pettypes/{id}", petId)
                .then()
                .statusCode(204);
    }

    private void putPetTypeTestWithObject() {
        petType.setName("bug");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petType)
                .put("/pettypes/{id}", petType.getId())
                .then()
                .statusCode(204);
    }

    @Test
    public void getPetTypeByIdAfterPutTest() {
        putPetTypeTestWithObject();
        getPetTypeByIdTest();
    }
}
