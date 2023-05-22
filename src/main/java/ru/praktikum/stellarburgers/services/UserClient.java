package ru.praktikum.stellarburgers.services;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.stellarburgers.model.User;

import static io.restassured.RestAssured.given;

public class UserClient extends RestAssuredClient {
    public static final String AUTH_REGISTER = "auth/register";
    public static final String AUTH_LOGIN = "auth/login";
    public static final String AUTH_USER = "auth/user";
    private String accessToken;

    private void getTokens(ValidatableResponse response) {
        if (response.extract().statusCode() == 200) {
            accessToken = response.extract().jsonPath().getString("accessToken").replace("Bearer ", "");
        }
    }

    @Step("Отправка POST запроса к /auth/register")
    public void registerUser(User user) {
        ValidatableResponse validatableResponse = given()
                .spec(getBaseSpec())
                .and()
                .body(user)
                .when()
                .post(AUTH_REGISTER)
                .then();
        getTokens(validatableResponse);
    }

    @Step("Отправка DELETE запроса к /auth/user")
    public void deleteUser() {
        if (accessToken == null) {
            return;
        }

        given()
                .spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .when()
                .delete(AUTH_USER)
                .then()
                .statusCode(202);
    }

}
