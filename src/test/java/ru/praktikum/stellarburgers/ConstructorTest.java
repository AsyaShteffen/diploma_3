package ru.praktikum.stellarburgers;

import com.codeborne.selenide.Condition;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.stellarburgers.pageobjects.MainPage;
import ru.praktikum.stellarburgers.services.DriverInfo;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@RunWith(Parameterized.class)
public class ConstructorTest {
    private static final DriverInfo driverInfo = new DriverInfo();
    private static MainPage mainPage;
    private final String driverPath;

    public ConstructorTest(String driverPath) {
        this.driverPath = driverPath;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {driverInfo.chromeDriverAbsolutePath},
                {driverInfo.yandexDriverAbsolutePath},
        };
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        mainPage = open(MainPage.URL_BASE, MainPage.class);
    }

    @After
    public void tearDown() {
        getWebDriver().quit();
    }

    @Test
    @DisplayName("Проверка перехода к разделу Булки")
    public void openBunsBlockTest() {
        mainPage.clickSaucesSection();
        mainPage.clickBunsSection();
        mainPage.getTextBunsBlock().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Проверка перехода к разделу Соусы")
    public void openSaucesBlockTest() {
        mainPage.clickSaucesSection();
        mainPage.getTextSaucesBlock().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Проверка перехода к разделу Начинки")
    public void openFillingsBlockTest() {
        mainPage.clickFillingsSection();
        mainPage.getTextFillingsBlock().shouldBe(Condition.visible);
    }
}