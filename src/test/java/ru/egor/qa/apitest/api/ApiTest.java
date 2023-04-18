package ru.egor.qa.apitest.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ApiTest {

    private final String reqres = "https://reqres.in/api/";
    //    Проверка статус-кода ответа:
    @Test
    public void testStatusCode() {
        Response response = RestAssured.given().log().all().get(reqres + "users?page=2");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    //    Проверка значения поля "page" в ответе:
    @Test
    public void testPageValue() {
        Response response = RestAssured.given().log().all().get(reqres + "users?page=2");
        int pageValue = response.jsonPath().getInt("page");
        Assert.assertEquals(pageValue, 2);
    }

    //    Проверка количества элементов в массиве "data":
    @Test
    public void testDataArraySize() {
        Response response = RestAssured.given().log().all().get(reqres + "users?page=2");
        List<Object> dataArray = response.jsonPath().getList("data");
        Assert.assertEquals(dataArray.size(), 6);
    }

    //    Проверка значения поля "id" у первого элемента массива "data":
    @Test
    public void testFirstDataId() {
        Response response = RestAssured.given().log().all().get(reqres + "users?page=2");
        int firstDataId = response.jsonPath().getInt("data[0].id");
        Assert.assertEquals(firstDataId, 7);
    }
}
