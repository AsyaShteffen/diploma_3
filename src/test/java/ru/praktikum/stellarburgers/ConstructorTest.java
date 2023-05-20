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
import static org.junit.Assert.assertEquals;

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
    @DisplayName("Проверка перехода на таб Булки")
    public void openBunsTabTest() {
        mainPage.clickSaucesSection();
        mainPage.clickBunsSection();
        String expectedTab = "Булки";
        String currentTab = mainPage.getCurrentTabText();
        assertEquals("Таб не выбран", expectedTab, currentTab);
    }

    @Test
    @DisplayName("Проверка перехода на таб Соусы")
    public void openSaucesTabTest() {
        mainPage.clickSaucesSection();
        String expectedTab = "Соусы";
        String currentTab = mainPage.getCurrentTabText();
        assertEquals("Таб не выбран", expectedTab, currentTab);
    }

    @Test
    @DisplayName("Проверка перехода на таб Начинки")
    public void openFillingsTabTest() {
        mainPage.clickFillingsSection();
        String expectedTab = "Начинки";
        String currentTab = mainPage.getCurrentTabText();
        assertEquals("Таб не выбран", expectedTab, currentTab);
    }
}