package com.automation.petclinic.restAssured;

import com.automation.petclinic.conf.Configuration;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class SpecialtyTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = Configuration.getInstance().getApiUrl();
        RestAssured.port = Configuration.getInstance().getApiPort();
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void getSpecialtyTest(){
        RestAssured.given()
                .get("/specialties")
                .then()
                .statusCode(200)
                .body("id", hasItems(1,2))
                .body("name", hasItems("radiology", "surgery"))
                .log().all();
    }

    @Test
    public void getSpecialtyByIdTest(){
        int id = 1;
        RestAssured.given()
                .get("/specialties/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo( id))
                .body("name", equalTo("radiology"))
                .log().all();
    }

}
