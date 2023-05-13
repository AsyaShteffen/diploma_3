package ru.praktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage extends CommonButtons {
    public final static String URL_BASE = "https://stellarburgers.nomoreparties.site/";

    // кнопка войти в аккаунт
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement logInButton;

    // кнопка булок
    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bunsButton;

    // кнопка соусов
    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement saucesButton;

    // кнопка начинок
    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement fillingsButton;

    // текст блока булки
    @FindBy(how = How.XPATH, using = ".//h2[text()='Булки']")
    private SelenideElement bunsText;

    // текст блока соусы
    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
    private SelenideElement saucesText;

    // текст блока начинки
    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
    private SelenideElement fillingsText;

    // кнопка "Оформить заказ"
    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement createOrderButton;

    @Step("Клик по кнопке войти в аккаунт")
    public void clickLogInButton() {
        logInButton.shouldBe(Condition.visible).click();
    }

    @Step("Клик по кнопке булки")
    public void clickBunsSection() {
        bunsButton.shouldBe(Condition.visible).click();
    }

    @Step("Клик по кнопке соусы")
    public void clickSaucesSection() {
        saucesButton.shouldBe(Condition.visible).click();
    }

    @Step("Клик по кнопке начинки")
    public void clickFillingsSection() {
        fillingsButton.shouldBe(Condition.visible).click();
    }

    @Step("Получение текста блока булочки")
    public SelenideElement getTextBunsBlock() {
        return bunsText;
    }

    @Step("Получение текста блока соусы")
    public SelenideElement getTextSaucesBlock() {
        return saucesText;
    }

    @Step("Получение текста блока начинки")
    public SelenideElement getTextFillingsBlock() {
        return fillingsText;
    }

    public SelenideElement getCreateOrderButton() {
        return createOrderButton;
    }
}
