package com.automation.petclinic.restAssured;

import com.automation.petclinic.conf.Configuration;
import com.automation.petclinic.model.Pet;
import com.automation.petclinic.model.Specialty;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;


public class SpecialtyTest2 {

    private Specialty specialty;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = Configuration.getInstance().getApiUrl();
        RestAssured.port = Configuration.getInstance().getApiPort();
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }

    @BeforeMethod
    public void createPet() {
        specialty = postSpecialtyTestWithObject();
    }

    @AfterMethod
    public void deletePet() {
        deleteSpecialtyByIdTest(specialty.getId());
    }

    @Test
    public void getSpecialtyByIdTest() {
        RestAssured.given()
                .get("/specialties/{id}", specialty.getId())
                .then()
                .statusCode(200)
                .body("id", equalTo(specialty.getId()))
                .body("name", equalTo(specialty.getName()))
                .log().all();
    }


    private Specialty postSpecialtyTestWithObject() {
        Specialty specialty = new Specialty();
        specialty.setName("dantist");
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(specialty)
                .post("/specialties")
                .then()
                .statusCode(201)
                .extract().body()
                .as(Specialty.class);
    }

    public void deleteSpecialtyByIdTest(int id) {
        RestAssured.given()
                .log().all()
                .delete("/specialties/{id}", id)
                .then()
                .statusCode(204);
    }


    private void putSpecialtyTestWithObject() {
        specialty.setName("ophthalmologist");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(specialty)
                .put("/specialties/{id}", specialty.getId())
                .then()
                .statusCode(204);
    }

    @Test
    public void getSpecialtyByIdAfterPutTest() {
        putSpecialtyTestWithObject();
        getSpecialtyByIdTest();
    }
}
