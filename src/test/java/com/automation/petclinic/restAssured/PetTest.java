package com.automation.petclinic.restAssured;

import com.automation.petclinic.conf.Configuration;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
@Epic("Petclinic")
@Feature("Pet")

public class PetTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = Configuration.getInstance().getApiUrl();
        RestAssured.port = Configuration.getInstance().getApiPort();
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void getPetTest(){
        RestAssured.given()
                .get("/pets")
                .then()
                .statusCode(200)
                .body("id", hasItem(1))
                .body("name", hasItem("Leo"))
                .body("birthDate", hasItem("2010/09/07"))
                .log().all();
    }

    @Test
    public void getPetByIdTest(){
        int id = 1;
        RestAssured.given()
                .get("/pets/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo( id))
                .body("name", equalTo("Leo"))
                .body("birthDate", equalTo("2010/09/07"))
                .log().all();
    }

    @Test
    public void getPetTypesTest(){
        RestAssured.given()
                .get("/pets/pettypes")
                .then()
                .statusCode(200)
                .body("id", hasItems(1, 2))
                .body("name", hasItems("cat", "dog"))
                .log().all();
    }


}
