package ru.egor.qa.apitest.api;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ApiTest extends BeforeTest{

    private final String reqResBaseUrl = "https://reqres.in/";
    private final String listUsers = "api/users?page=2";
    private final String singleUser = "api/users/2";
    private final Utils as = new Utils();

    @Test
    @Description("Проверка статус-кода ответа")
    public void testStatusCode() {
        Response response = as.getResponse(reqResBaseUrl, listUsers);
        as.checkStatusCode(response,200);
    }

    @Test
    @Description("Проверка значения поля \"page\" в ответе")
    public void testPageValue() {
        Response response = as.getResponse(reqResBaseUrl, listUsers);
        as.checkStatusCode(response, 200);
        Assert.assertEquals(response.jsonPath().getInt("page"), 2);
    }

    @Test
    @Description("Проверка количества элементов в массиве \"data\"")
    public void testDataArraySize() {
        Response response = as.getResponse(reqResBaseUrl, listUsers);
        List<Object> dataArray = response.jsonPath().getList("data");
        as.checkStatusCode(response, 200);
        Assert.assertEquals(dataArray.size(), 6);
    }

    @Test
    @Description("Проверка значения поля \"id\" у первого элемента массива \"data\"")
    public void testFirstDataId() {
        Response response = as.getResponse(reqResBaseUrl, listUsers);
        as.checkStatusCode(response, 200);
        Assert.assertEquals(response.jsonPath().getInt("data[0].id"), 7);
    }


    @Test
    @Description("")
    public void singleUserTest(){
        Response response = as.getResponse(reqResBaseUrl, singleUser);
        as.checkStatusCode(response, 200);
        Assert.assertEquals(response.jsonPath().getInt("data.id"), 2);
        Assert.assertEquals(response.jsonPath().get("data.first_name"), "Janet");
    }

}
