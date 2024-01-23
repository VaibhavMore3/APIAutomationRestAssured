package org.automation.misc;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestFile {

    @Test
    public static void request(){
        RestAssured
                .given().baseUri("https://restful-booker.herokuapp.com/ping")
                .when().get()
                .then().log().all().statusCode(201);
    }
}
