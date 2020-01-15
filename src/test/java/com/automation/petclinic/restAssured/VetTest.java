package com.automation.petclinic.restAssured;

import com.automation.petclinic.conf.Configuration;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class VetTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = Configuration.getInstance().getApiUrl();
        RestAssured.port = Configuration.getInstance().getApiPort();
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void getVetTest(){
        RestAssured.given()
                .get("/vets")
                .then()
                .statusCode(200)
                .body("id", hasItems(1,2))
                .body("firstName", hasItems("James", "Helen"))
                .body("lastName", hasItems("Carter", "Leary"))
                .log().all();
    }

    @Test
    public void getVetrByIdTest(){
        int id = 1;
        RestAssured.given()
                .get("/vets/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo( id))
                .body("firstName", equalTo("James"))
                .body("lastName", equalTo("Carter"))
                .log().all();
    }



}
