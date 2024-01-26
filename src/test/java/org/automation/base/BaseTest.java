package org.automation.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.automation.Actions.AssertActions;
import org.automation.endpoints.APIConstants;
import org.automation.modules.PayloadManager;
import org.testng.annotations.BeforeMethod;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {

    public RequestSpecification requestSpecification;

    public Response response;

    public AssertActions assertActions;

    public PayloadManager payloadManager;

    public ValidatableResponse validatableResponse;

    public JsonPath jsonPath;


    @BeforeMethod
    public void setUp(){

        payloadManager = new PayloadManager();

        assertActions = new AssertActions();

        requestSpecification = RestAssured.given()
                                    .baseUri(APIConstants.baseURL)
                                        .contentType(ContentType.JSON);

        // Another way using RequestSpecBuilder

//        requestSpecification = new RequestSpecBuilder()
//                                .setBaseUri(APIConstants.baseURL)
//                                  .addHeader("Content-Type", "application/json")
//                                     .build().log().all();

    }


    public String getToken() throws JsonProcessingException {

      String payload = payloadManager.setToken();

//      requestSpecification = RestAssured.given().baseUri(APIConstants.baseURL).basePath(APIConstants.authPath);
      requestSpecification = RestAssured.given().baseUri(APIConstants.baseURL+APIConstants.authPath);

      response = requestSpecification.contentType(ContentType.JSON)
              .body(payload).when().post();

      jsonPath = new JsonPath(response.asString());
      return jsonPath.getString("token");

    }

}
