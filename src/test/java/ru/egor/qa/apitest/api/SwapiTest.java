package ru.egor.qa.apitest.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.egor.qa.apitest.readProperties.ConfigProvider;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SwapiTest extends BeforeTest{



    @Test
    public void checkCode() {
        given().baseUri(ConfigProvider.BASE_URL_SWAPI)
                .when().contentType(ContentType.JSON).get()
                .then().log().all().statusCode(200);
    }

    @Test
    public void pathPeople() {
        given().baseUri(ConfigProvider.BASE_URL_SWAPI)
                .when().contentType(ContentType.JSON).get("people/")
                .then().log().all().statusCode(200);
    }

    @Test
    public void checkVehicles() {
        List<VehiclesData> vehicles = given()
                .baseUri(ConfigProvider.BASE_URL_SWAPI)
                .basePath("vehicles/")
                .log().body()
                .when()
                .contentType(ContentType.JSON).get()
                .then().log().body().statusCode(200)
                .extract().body().jsonPath().getList("results", VehiclesData.class);
        List<String> name = vehicles.stream().map(VehiclesData::getName).toList();
        Assertions.assertEquals(name.size(), 10);
    }

    @Test
    public void checkPeople() {
        List<PeopleData> people = given()
                .baseUri(ConfigProvider.BASE_URL_SWAPI)
                .basePath("people/")
                .log().body()
                .when()
                .contentType(ContentType.JSON).get()
                .then().log().body()
                .extract().body().jsonPath().getList("results", PeopleData.class);
        //Получаем из списка все имена
        List<String> name = people.stream().map(PeopleData::getName).toList();
        //Получаем из списка все гендеры
        List<String> gender = people.stream().map(PeopleData::getGender).toList();
        people.forEach(x-> Assertions.assertTrue(x.getName().contains(x.getName())));
    }
}
