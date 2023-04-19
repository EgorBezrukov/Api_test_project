package ru.egor.qa.apitest.api;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ApiTest {

    private final String reqResBaseUrl = "https://reqres.in/";

    @Test
    @Description("роверка статус-кода ответа")
    public void testStatusCode() {
        Response response = RestAssured.
                given().baseUri(reqResBaseUrl).
                log().all().
                get("api/users?page=2");
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    @Description("Проверка значения поля \"page\" в ответе")
    public void testPageValue() {
        Response response = RestAssured.
                given().baseUri(reqResBaseUrl).
                log().all().
                get("api/users?page=2");
        Assert.assertEquals(response.jsonPath().getInt("page"), 2);
    }

    @Test
    @Description("Проверка количества элементов в массиве \"data\"")
    public void testDataArraySize() {
        Response response = RestAssured.
                given().baseUri(reqResBaseUrl).
                log().all().
                get("api/users?page=2");
        List<Object> dataArray = response.jsonPath().getList("data");
        Assert.assertEquals(dataArray.size(), 6);
    }

    @Test
    @Description("Проверка значения поля \"id\" у первого элемента массива \"data\"")
    public void testFirstDataId() {
        Response response = RestAssured.
                given().baseUri(reqResBaseUrl).
                log().all().
                get("users?page=2");
        Assert.assertEquals(response.jsonPath().getInt("data[0].id"), 7);
    }

}
