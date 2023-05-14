package ru.praktikum.stellarburgers;

import com.codeborne.selenide.WebDriverConditions;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.stellarburgers.model.User;
import ru.praktikum.stellarburgers.pageobjects.AccountProfilePage;
import ru.praktikum.stellarburgers.pageobjects.LoginPage;
import ru.praktikum.stellarburgers.pageobjects.MainPage;
import ru.praktikum.stellarburgers.services.DriverInfo;
import ru.praktikum.stellarburgers.services.UserClient;
import ru.praktikum.stellarburgers.services.UserGenerator;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@RunWith(Parameterized.class)
public class OpenProfileTest {
    private static final DriverInfo driverInfo = new DriverInfo();
    static MainPage mainPage;
    private final UserClient userClient = new UserClient();
    private final String driverPath;

    public OpenProfileTest(String driverPath) {
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
        System.setProperty("webdriver.chrome.driver", driverPath);
        mainPage = open(MainPage.URL_BASE, MainPage.class);
        User user = UserGenerator.random();
        userClient.registerUser(user);
        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(user.getEmail(), user.getPassword());
        webdriver().shouldHave(WebDriverConditions.url(MainPage.URL_BASE));
    }

    @After
    public void tearDown() {
        getWebDriver().quit();
        userClient.deleteUser();
    }

    @Test
    @DisplayName("Проверка перехода в «Личный кабинет»")
    public void openProfilePageTest() {
        mainPage.clickPersonalAreaButton();
        webdriver().shouldHave(WebDriverConditions.url(AccountProfilePage.URL_PROF));
    }

    @Test
    @DisplayName("Проверка перехода из Личного кабинета в Конструктор")
    public void openConstructorFromProfilePageTest() {
        mainPage.clickPersonalAreaButton();
        AccountProfilePage accountProfilePage = page(AccountProfilePage.class);
        accountProfilePage.clickConstructorButton();
        webdriver().shouldHave(WebDriverConditions.url(MainPage.URL_BASE));
    }

    @Test
    @DisplayName("Проверка перехода из Личного кабинета на главную страницу кликом на логотип")
    public void openMainPageFromProfilePageTest() {
        mainPage.clickPersonalAreaButton();
        AccountProfilePage accountProfilePage = page(AccountProfilePage.class);
        accountProfilePage.clickConstructorButton();
        webdriver().shouldHave(WebDriverConditions.url(MainPage.URL_BASE));
    }
}