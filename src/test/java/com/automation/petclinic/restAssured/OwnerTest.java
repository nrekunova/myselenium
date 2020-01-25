package com.automation.petclinic.restAssured;

import com.automation.petclinic.conf.Configuration;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
@Epic("Petclinic")
@Feature("Owner")
public class OwnerTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = Configuration.getInstance().getApiUrl();
        RestAssured.port = Configuration.getInstance().getApiPort();
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void getOwnerTest(){
        RestAssured.given()
                .get("/owners")
                .then()
                .statusCode(200)
                .body("id", hasItems(1,2))
                .body("firstName", hasItems("George", "Betty"))
                .body("lastName", hasItems("Franklin", "Davis"))
                .body("address", hasItems("110 W. Liberty St.", "638 Cardinal Ave."))
                .body("city", hasItems("Madison", "Sun Prairie"))
                .body("telephone", hasItems("6085551023", "6085551749"))
                .log().all();
    }

    @Test
    public void getOwnerByIdTest(){
        int id = 1;
        RestAssured.given()
                .get("/owners/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo( id))
                .body("firstName", equalTo("George"))
                .body("lastName", equalTo("Franklin"))
                .body("address", equalTo("110 W. Liberty St."))
                .body("city", equalTo("Madison"))
                .body("telephone", equalTo("6085551023"))
                .log().all();
    }

    @Test
    public void getOwnersByLastNameTest(){
        String name = "Davis";
        RestAssured.given()
                .get("/owners/*/lastname/{name}", name)
                .then()
                .statusCode(200)
                .body("id", hasItems(4,2))
                .body("firstName", hasItems("Harold", "Betty"))
                .body("lastName", hasItems("Davis", "Davis"))
                .body("address", hasItems("563 Friendly St.", "638 Cardinal Ave."))
                .body("city", hasItems("Windsor", "Sun Prairie"))
                .body("telephone", hasItems("6085553198", "6085551749"))
                .log().all();
    }


}
