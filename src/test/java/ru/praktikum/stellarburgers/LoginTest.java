package ru.praktikum.stellarburgers;

import com.codeborne.selenide.Condition;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.stellarburgers.model.User;
import ru.praktikum.stellarburgers.pageobjects.ForgotPasswordPage;
import ru.praktikum.stellarburgers.pageobjects.LoginPage;
import ru.praktikum.stellarburgers.pageobjects.MainPage;
import ru.praktikum.stellarburgers.pageobjects.RegisterPage;
import ru.praktikum.stellarburgers.services.DriverInfo;
import ru.praktikum.stellarburgers.services.UserClient;
import ru.praktikum.stellarburgers.services.UserGenerator;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@RunWith(Parameterized.class)
public class LoginTest {
    private static final DriverInfo driverInfo = new DriverInfo();
    public static MainPage mainPage;
    private final UserClient userClient = new UserClient();
    private final String driverPath;
    private User user;

    public LoginTest(String driverPath) {
        this.driverPath = driverPath;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {driverInfo.getChromeDriverAbsolutePath()},
                {driverInfo.getYandexDriverAbsolutePath()},
        };
    }

    @Before
    public void setUp() {
        user = UserGenerator.random();
        userClient.registerUser(user);
        System.setProperty("webdriver.chrome.driver", driverPath);
        mainPage = open(MainPage.URL_BASE, MainPage.class);
    }

    @After
    public void tearDown() {
        getWebDriver().quit();
        if (user != null) {
            userClient.deleteUser();
        }
    }

    @Test
    @DisplayName("Логин по кнопке Войти в аккаунт на главной")
    public void loginWithLogInButtonTest() {
        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(user.getEmail(), user.getPassword());
        mainPage.getCreateOrderButton().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Логин через кнопку «Личный кабинет»")
    public void loginWithAccountProfileTest() {
        mainPage.clickPersonalAreaButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(user.getEmail(), user.getPassword());
        mainPage.getCreateOrderButton().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Логин через кнопку в форме регистрации")
    public void loginWithRegistrationPageTest() {
        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterButton();
        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.clickLogInButton();
        loginPage.loginUser(user.getEmail(), user.getPassword());
        mainPage.getCreateOrderButton().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Логин через кнопку в форме восстановления пароля")
    public void loginWithRecoverPasswordTest() {
        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickForgotPasswordButton();
        ForgotPasswordPage forgotPasswordPage = page(ForgotPasswordPage.class);
        forgotPasswordPage.clickLoginButton();
        loginPage.loginUser(user.getEmail(), user.getPassword());
        mainPage.getCreateOrderButton().shouldBe(Condition.visible);
    }
}