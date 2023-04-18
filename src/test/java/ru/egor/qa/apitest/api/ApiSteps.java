package ru.egor.qa.apitest.api;

import io.cucumber.java.ru.Когда;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;

public class ApiSteps {
    private String result;
    private String getResult() {
        return result;
    }
    @Когда("^(?:пользователь|он)? отправляет запрос на ресурс \"([^\"]*)\"$")
    public void sendRequest(String url) {
        result = String.valueOf(RestAssured.given().log().all().get(url).statusCode());
    }

    @Когда("^(?:пользователь|он)? проверяет, что значение кода равно \"([^\"]*)\"$")
    public void checkStatusCode(Object code) {
        Assertions.assertEquals(code, getResult());
    }
}
