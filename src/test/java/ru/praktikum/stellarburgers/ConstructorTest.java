package ru.praktikum.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.stellarburgers.pageobjects.MainPage;
import ru.praktikum.stellarburgers.services.Driver;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertEquals;

public class ConstructorTest {
    private static final Driver driverInfo = new Driver();
    private static MainPage mainPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverInfo.getDriverPath());
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