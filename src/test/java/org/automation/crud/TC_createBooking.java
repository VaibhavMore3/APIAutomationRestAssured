package org.automation.crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import org.automation.base.BaseTest;
import org.automation.endpoints.APIConstants;
import org.automation.payloads.response.BookingResponse;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TC_createBooking extends BaseTest {

    // Allure report info
    @Owner("Vaibhav")
    @Description("Verify that the create booking works & ID is generated")
    @Severity(SeverityLevel.CRITICAL)

    // TestNG
    @Test

    public void testCreateBooking_1() throws JsonProcessingException {

        requestSpecification.baseUri(APIConstants.baseURL+APIConstants.authPath);

        response = requestSpecification.when().body(payloadManager.createPayload()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse = payloadManager.JsonToObject(response.asString());
        assertThat(bookingResponse.getBookingid().toString()).isNotEmpty().isNotBlank().isNotNull();


    }

}
