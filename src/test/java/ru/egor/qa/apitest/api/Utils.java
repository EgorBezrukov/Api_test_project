package ru.egor.qa.apitest.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

public class Utils {
    public Utils() {
    }

    static void checkStatusCode(Response response, int code) {
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    Response getResponse(String baseUrl, String params) {
        Response response = RestAssured.
                given().baseUri(baseUrl).
                get(params);
        return response;
    }
}