package org.automation.crud;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.automation.base.BaseTest;
import org.automation.endpoints.APIConstants;
import org.automation.payloads.request.Booking;
import org.automation.payloads.response.BookingResponse;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import static org.assertj.core.api.Assertions.assertThat;


public class TC_Integration extends BaseTest {

    String token;
    String bookingID;
    String bookingIDPOJO;

//    private static final Logger log = LogManager.getLogger(TC_Integration.class);

    @Test
    public void testCreateBooking() throws JsonProcessingException {
        token = getToken();
        assertThat(token).isNotNull().isNotEmpty();

        requestSpecification.baseUri(APIConstants.baseURL+APIConstants.CREATE_BOOKING);
        response = requestSpecification.when().body(payloadManager.createPayload()).post();
        validatableResponse = response.then().log().all();
        jsonPath = JsonPath.from(response.asString());
        validatableResponse.statusCode(200);

        // simple way -
        bookingID = jsonPath.getString("bookingid");


        //Creating a class (optional)
        BookingResponse bookingResponse = payloadManager.JsonToObject(response.asString());
        bookingIDPOJO = bookingResponse.getBookingid().toString();


    }


    @Test(dependsOnMethods = {"testCreateBooking"})
    public void testCreateAndUpdateBooking() throws JsonProcessingException {

        requestSpecification.basePath(APIConstants.updateBooking+"/"+bookingID);
        response = RestAssured.given().spec(requestSpecification).cookie("token",token)
                .when().body(payloadManager.updatePayload()).put();
        validatableResponse = response.then().log().all();
        //validatableResponse.body("firstname", Matchers.is("ABC"));

        Booking bookingResponse = payloadManager.JsonToObjectPUT(response.asString());
        assertThat(bookingResponse.getFirstname()).isEqualTo("ABC").isNotNull();
        assertThat(bookingResponse.getLastname()).isEqualTo("XYZ").isNotNull();


    }

    @Test (dependsOnMethods = {"testCreateAndUpdateBooking"})
    public void testDeleteBooking(){

        requestSpecification.basePath(APIConstants.updateBooking+"/"+bookingID).cookie("token",token);
        validatableResponse = RestAssured.given().spec(requestSpecification).auth().basic("admin","password123")
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);

    }

    @Test (dependsOnMethods = {"testDeleteBooking"})
    public void testDeletedBookingByGET(){

        requestSpecification.basePath(APIConstants.updateBooking+"/"+bookingID);
        response = RestAssured.given().spec(requestSpecification).when().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);

    }

}
