package com.thetestingacademy.assertions;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

public class AssertActions {

//    Common Assertions which can be reused
//     We have done Method Overloading below by changing the parameters

    public void verifyResponseBody(String actual, String expected, String description) {
        assertEquals(actual, expected, description);
    }

    public void verifyResponseBody(int actual, int expected, String description) {
        assertEquals(actual, expected, description);
    }

    public void verifyStatusCode(Response response, Integer expected) {
        assertEquals(response.getStatusCode(),expected);
    }

}
