package com.thetestingacademy.tests.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.BookingResponse;
import com.thetestingacademy.utils.PropertyReader;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class TestCreateBookingPost extends BaseTest {

    @Link(name = "Link to TC", url = "https://bugz.atlassian.net/browse/RBT-4")
    @Issue("JIRA_RBT-4")
    @TmsLink("RBT-4")
    @Owner("Arshad")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that POST request is working fine")
    @Test
    public void testVerifyCreateBookingPOST01(){

   /* Since all Rest Assured classes are initialized and Basurl is alreday set in Base Test Class
         We can start with setting base path for Create Booking Content Type also already set*/

        request.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        request.body(payloadManager.PayloadForCreateBookingAsString());    //PayloadManager is imported in Base Test Hence we are able to use it here

        //This is the code for Setup1 function and this will fail when executed with Set up 2 in Base Test
        response = request.when().post();
//        response = RestAssured.given(request).when().body(payloadManager.PayloadForCreateBookingAsString()).post();

        validate = response.then();
        validate.statusCode(200);

//       Default Rest Assured Matchers
        validate.body("booking.firstname", Matchers.equalTo(PropertyReader.readKey("booking.post.firstname")));

        BookingResponse bookingresponse = payloadManager.bookingResponse(response.asString());
//     AssertJ Assertions
        assertThat(bookingresponse.getBookingid()).isNotBlank().isNotNull();
        assertThat(bookingresponse.getBooking().getFirstname()).isEqualTo(PropertyReader.readKey("booking.post.firstname"));
        assertThat(bookingresponse.getBooking().getLastname()).isEqualTo(PropertyReader.readKey("booking.post.lastname"));

//      TestNG Assertion (Using TestNG Assertions via the Default Assertion class we created)
       assertActions.verifyStatusCode(response,Integer.valueOf(PropertyReader.readKey("booking.post.success.statuscode")));

//       Direct TestNG Assertions
        Assert.assertEquals(response.getStatusCode(), Integer.valueOf(PropertyReader.readKey("booking.post.success.statuscode")));



    }

    @Link(name = "Link to TC", url = "https://bugz.atlassian.net/browse/RBT-4")
    @Issue("JIRA_RBT-5")
    @TmsLink("RBT-5")
    @Owner("Arshad")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that POST request is working fine with little difference in Rest Assured code")
    @Test()
    public void testVerifyCreateBookingPOST02(){

   /* Since all Rest Assured classes are initialized and Base url is already set in Base Test Class
         We can start with setting base path for Create Booking Content Type also already set*/

        request.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        //This is the code for Setup2 function in Base Test but also runs with setup2
        response = RestAssured.given(request).when().body(payloadManager.PayloadForCreateBookingAsString()).post();

        validate = response.then().log().all();
        validate.statusCode(200);

//       Default Rest Assured Matchers
        validate.body("booking.firstname", Matchers.equalTo(PropertyReader.readKey("booking.post.firstname")));

    BookingResponse bookingresponse = payloadManager.bookingResponse(response.asString());
    //     AssertJ Assertions
    assertThat(bookingresponse.getBookingid()).isNotBlank().isNotNull();
    assertThat(bookingresponse.getBooking().getFirstname()).isEqualTo(PropertyReader.readKey("booking.post.firstname"));
    assertThat(bookingresponse.getBooking().getLastname()).isEqualTo(PropertyReader.readKey("booking.post.lastname"));

//      TestNG Assertion (Using TestNG Assertions via the Default Assertion class we created)
       assertActions.verifyStatusCode(response, Integer.valueOf(PropertyReader.readKey("booking.post.success.statuscode")));




//       Direct TestNG Assertions
        Assert.assertEquals(response.getStatusCode(), Integer.valueOf(PropertyReader.readKey("booking.post.success.statuscode")));
    }

}
