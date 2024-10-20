package com.thetestingacademy.tests.integration;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.listeners.RetryAnalyzer;
import com.thetestingacademy.pojos.Booking;
import com.thetestingacademy.pojos.BookingResponse;
import com.thetestingacademy.utils.PropertyReader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


@Test(retryAnalyzer = RetryAnalyzer.class)
public class TestIntegrationFlowRetry extends BaseTest {
// Integration Flow 1
    // Create A Booking
    // Get booking
    // Create a Token
    // Update the Booking
    // Delete the Booking


    @Test(groups = "integration", priority = 1)
    @Owner("Arshad")
    @Description("TC#INT1 - Step 1. Create a Booking with POST Request")
    public void testCreateBooking(ITestContext iTestContext){

        iTestContext.setAttribute("token", getToken());
        String token = (String) iTestContext.getAttribute("token");
        System.out.println(token);

        request.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        request.body(payloadManager.PayloadForCreateBookingAsString()).log().all();    //PayloadManager is imported in Base Test Hence we are able to use it here


        response = request.when().post();

        validate = response.then().log().all();
        validate.statusCode(200);


        BookingResponse bookingresponse = payloadManager.bookingResponse(response.asString());
////     AssertJ Assertions
        assertThat(bookingresponse.getBookingid()).isNotBlank().isNotNull();
        assertThat(bookingresponse.getBooking().getFirstname()).isEqualTo(PropertyReader.readKey("booking.post.firstname"));
        assertThat(bookingresponse.getBooking().getLastname()).isEqualTo(PropertyReader.readKey("booking.post.lastname"));


//     Let's fetch the token from base test and booking id from Create Booking Response and store

        iTestContext.setAttribute("bookingid", bookingresponse.getBookingid());

//     Now the above 2 values are available for the rest of the Tests in this class


    }


    @Test(groups = "integration", priority = 2)
    @Owner("Arshad")
    @Description("TC#INT1 - Step 2. Verify the created booking id using Get Request")
    public void testVerifyBookingId(ITestContext iTestContext){
        //Required values - Booking id

//      ITestContext.getAttribute returns Object

        String bookingid = (String)iTestContext.getAttribute("bookingid");
        System.out.println(bookingid);



        //Base URL already set in Base Test Class
//        request = RestAssured.given();
        request.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid);
        request.log().all();
        response = request.when().get();
        validate = response.then().log().all();
        validate.statusCode(200);

//      Here we can use the same Booking class to store the response

        Booking booking = payloadManager.getBookingResponse(response.asString());

        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readKey("booking.post.firstname"));
        assertThat(booking.getLastname()).isEqualTo(PropertyReader.readKey("booking.post.lastname"));


    }


    @Test(groups = "integration", priority = 3)
    @Owner("Arshad")
    @Description("TC#INT1 - Step 3. Update the Created Booking Id using PUT Request")
    public void testUpdateBookingByID(ITestContext iTestContext){

        String token = (String) iTestContext.getAttribute("token");
        String bookingid = (String)iTestContext.getAttribute("bookingid");

        request.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid);
        request.cookie("token" , token);
        request.body(payloadManager.PayloadforUpdateBookignAsString()).log().all();

        response = request.when().put();

        validate= response.then().log().all();
        validate.statusCode(200);

        Booking booking = payloadManager.getBookingResponse(response.asString());

        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readKey("booking.put.firstname"));
        assertThat(booking.getLastname()).isEqualTo(PropertyReader.readKey("booking.put.lastname"));


    }


    @Test(groups = "integration", priority = 4)
    @Owner("Arshad")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID which is Created")
    public void testDeleteBookingById(ITestContext iTestContext){

        String token = (String) iTestContext.getAttribute("token");
        Assert.assertTrue(true);

        String bookingid = (String) iTestContext.getAttribute("bookingid");

        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathDELETE);

        request.basePath(basePathDELETE).cookie("token", token).log().all();

        response = request.when().delete();
        validate = response.then().log().all();

        validate.statusCode(200);



    }
}
