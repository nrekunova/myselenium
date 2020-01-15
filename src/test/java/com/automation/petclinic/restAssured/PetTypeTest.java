package com.automation.petclinic.restAssured;

import com.automation.petclinic.conf.Configuration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class PetTypeTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = Configuration.getInstance().getApiUrl();
        RestAssured.port = Configuration.getInstance().getApiPort();
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void getPetTypesTest(){
         RestAssured.given()
                .get("/pettypes")
                .then()
                .statusCode(200)
                .body("id", hasItems(1, 2))
                .body("name", hasItems("cat", "dog"))
                .log().all();
    }

    @Test
    public void getPetTypesByIdTest(){
        int petTypeId = 1;
        RestAssured.given()
                .get("/pettypes/{id}", petTypeId)
                .then()
                .statusCode(200)
                .body("id", equalTo( petTypeId))
                .body("name", equalTo("cat"))
                .log().all();
    }

}
