package com.thetestingacademy.base;

import io.restassured.RestAssured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import com.thetestingacademy.assertions.AssertActions;
import com.thetestingacademy.modules.PayloadManager;
import com.thetestingacademy.endpoints.APIConstants;

import io.restassured.path.json.JsonPath;

import org.testng.annotations.BeforeTest;

public class BaseTest {
//    Common to all Test Cases
//    Inheritance Concept - Father (Base Test) -> Test Case (Son)


//  Creating Ref/Obj for Rest Assured Classes
    public RequestSpecification request;
    public Response response;
    public ValidatableResponse validate;

//  Creating Ref/Obj for Required Classes we created in Frame work
    public AssertActions assertActions;
    public PayloadManager payloadManager;

//  Creating Ref for utilities functions which we will be using
    public JsonPath jsonPath;


    //  TC - Header

    @BeforeTest
    public void setUp1() {

        payloadManager = new PayloadManager();
        assertActions = new AssertActions();

//     Below code is the one we used so far but we can also write this with RequestSpecBuilder class
        request = RestAssured.given();
        request.log().all(); //log().all() gets executed only when request.when.post is called hence all the parameters are logged in console even though it is declared after this
        request.baseUri(APIConstants.BASE_URL);
        request.contentType(ContentType.JSON);


    }

//    @BeforeTest
//    public void setUp2() {
//
//        payloadManager = new PayloadManager();
//        assertActions = new AssertActions();
//
////     Below code is the one we used so far but we can also write this with RequestSpecBuilder class
////        request = RestAssured.given();
////        request.baseUri(APIConstants.BASE_URL)
////                .contentType(ContentType.JSON)
////                .log().all();
//
//        request = new RequestSpecBuilder()
//                .setBaseUri(APIConstants.BASE_URL) //Using Static Base Url using a CLass name
//                .addHeader("Content-Type", "application/json")
//                .build().log().all();
//
//    }




//   We can use this a Parent class for Test Cases applicable
//         In our framework we used for this as a parent class for the  CURD operations

}
