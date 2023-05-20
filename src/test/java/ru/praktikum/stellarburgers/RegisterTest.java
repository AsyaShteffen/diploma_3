package ru.praktikum.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverConditions;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.stellarburgers.model.User;
import ru.praktikum.stellarburgers.pageobjects.LoginPage;
import ru.praktikum.stellarburgers.pageobjects.RegisterPage;
import ru.praktikum.stellarburgers.services.Driver;
import ru.praktikum.stellarburgers.services.UserClient;
import ru.praktikum.stellarburgers.services.UserGenerator;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertEquals;

public class RegisterTest {
    private static final Driver driverInfo = new Driver();
    LoginPage loginPage;
    private RegisterPage registerPage;
    private UserClient userClient;
    private User user;


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverInfo.getDriverPath());
        registerPage = open(RegisterPage.URL_REG, RegisterPage.class);
        userClient = new UserClient();
    }

    @After
    public void tearDown() {
        getWebDriver().quit();
        if (user != null) {
            userClient.deleteUser();
        }
    }

    @Test
    @DisplayName("Проверка успешной регистрации пользователя")
    public void registerSuccessTest() {
        user = UserGenerator.random();
        registerPage.registerNewUser(user.getName(), user.getEmail(), user.getPassword());
        loginPage = page(LoginPage.class);
        webdriver().shouldHave(WebDriverConditions.url(LoginPage.URL_LOGIN));
    }

    @Test
    @DisplayName("Проверка ошибки - пароль менее 6 символов")
    public void passwordTooShortTest() {
        user = UserGenerator.shortPasswordUser();
        registerPage.registerNewUser(user.getName(), user.getEmail(), user.getPassword());
        registerPage.getInvalidPasswordErrorMessage().shouldBe(Condition.visible);
        assertEquals("Ожидаем ошибку о некорректном пароле", "Некорректный пароль", registerPage.getInvalidPasswordErrorMessage().getText());
    }
}