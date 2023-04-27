package ru.egor.qa.apitest.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;

public class BeforeTest {
    @BeforeEach
    public void setFilter() {
        RestAssured.filters(new AllureRestAssured());
    }
}