package com.automation.petclinic.restAssured;

import com.automation.petclinic.conf.Configuration;
import com.automation.petclinic.model.Owner;
import com.automation.petclinic.model.Pet;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;


public class OwnerTest2 {

    private Owner owner;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = Configuration.getInstance().getApiUrl();
        RestAssured.port = Configuration.getInstance().getApiPort();
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }

    @BeforeMethod
    public void createOwner() {
        owner = postOwnerTestWithObject();
    }

    @AfterMethod
    public void deleteOwner() {
        deleteOwnerByIdTest(owner.getId());
    }

    @Test
    public void getOwnerByIdTest() {
        RestAssured.given()
                .get("/owners/{id}", owner.getId())
                .then()
                .statusCode(200)
                .body("id", equalTo(owner.getId()))
                .body("firstName", equalTo(owner.getFirstName()))
                .body("lastName", equalTo(owner.getLastName()))
                .body("address", equalTo(owner.getAddress()))
                .body("city", equalTo(owner.getCity()))
                .body("telephone", equalTo(owner.getTelephone()))
                .log().all();
    }


    private Owner postOwnerTestWithObject() {
        Owner owner = new Owner();
        owner.setFirstName("testFirstNameApi");
        owner.setLastName("testLastNameApi");
        owner.setAddress("testAddress");
        owner.setCity("testCity");
        owner.setTelephone("123456");
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(owner)
                .log().all()
                .post("/owners")
                .then()
                .log().all()
                .statusCode(201)
                .extract().body()
                .as(Owner.class);
    }

    public void deleteOwnerByIdTest(int id) {
        RestAssured.given()
                .log().all()
                .delete("/owners/{id}", id)
                .then()
                .statusCode(204);
    }

    private void putOwnerTestWithObject() {
        owner.setFirstName("bug");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(owner)
                .put("/owners/{id}", owner.getId())
                .then()
                .statusCode(204);
    }

    @Test
    public void getOwnerByIdAfterPutTest() {
        putOwnerTestWithObject();
        getOwnerByIdTest();
    }
}
