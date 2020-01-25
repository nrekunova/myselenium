package com.automation.petclinic.restAssured;

import com.automation.petclinic.conf.Configuration;
import com.automation.petclinic.model.Owner;
import com.automation.petclinic.model.Vet;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;


@Epic("Petclinic")
@Feature("Veterinarian")
public class VetTest2 {

    private Vet vet;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = Configuration.getInstance().getApiUrl();
        RestAssured.port = Configuration.getInstance().getApiPort();
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Step("Create new veterinarian for test")
    @BeforeMethod
    public void createVet() {
        vet = postVetTestWithObject();
    }

    @Step("Delete veterinarian after test")
    @AfterMethod
    public void deleteVet() {
        deleteVetByIdTest(vet.getId());
    }

    @Step
    @Test
    public void getVetByIdTest() {
        RestAssured.given()
                .get("/vets/{id}", vet.getId())
                .then()
                .statusCode(200)
                .body("id", equalTo(vet.getId()))
                .body("firstName", equalTo(vet.getFirstName()))
                .body("lastName", equalTo(vet.getLastName()))
                .log().all();
    }


    private Vet postVetTestWithObject() {
        Vet vet = new Vet();
        vet.setFirstName("testFirstNameApi");
        vet.setLastName("testLastNameApi");

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(vet)
                .log().all()
                .post("/vets")
                .then()
                .log().all()
                .statusCode(201)
                .extract().body()
                .as(Vet.class);
    }

    @Step
    public void deleteVetByIdTest(int id) {
        RestAssured.given()
                .log().all()
                .delete("/vets/{id}", id)
                .then()
                .statusCode(204);
    }

    @Step
    private void putVetTestWithObject() {
        vet.setFirstName("bug");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(vet)
                .log().all()
                .put("/vets/{id}", vet.getId())
                .then()
                .log().all()
                .statusCode(204);
    }

    @Test
    public void getVetByIdAfterPutTest() {
        putVetTestWithObject();
        getVetByIdTest();
    }
}
