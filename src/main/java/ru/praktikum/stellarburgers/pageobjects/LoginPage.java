package ru.praktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends CommonButtons {
    public static final String URL_LOGIN = MainPage.URL_BASE + "login";

    // поле ввода email
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement emailField;

    // поле ввода пароля
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement passwordField;

    // кнопка войти
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement loginButton;

    // кнопка регистрации
    @FindBy(how = How.XPATH, using = ".//div/main/div/div/p[1]/a")
    private SelenideElement registerButton;

    // кнопка восстановления пароля
    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    private SelenideElement forgotPasswordButton;

    // заполнение поля email
    public void fillEmail(String email) {
        emailField.setValue(email);
    }

    // заполнение поля пароль
    public void fillPassword(String password) {
        passwordField.setValue(password);
    }

    // клик на кнопку логин
    public void clickLoginButton() {
        loginButton.shouldBe(Condition.visible).click();
    }

    @Step("Логин пользователя")
    public void loginUser(String email, String password) {
        fillEmail(email);
        fillPassword(password);
        clickLoginButton();
    }

    @Step("Клик на кнопку зарегистрироваться")
    public void clickRegisterButton() {
        registerButton.shouldBe(Condition.visible).click();
    }

    @Step("Клик на кнопку восстановления пароля")
    public void clickForgotPasswordButton() {
        forgotPasswordButton.shouldBe(Condition.visible).click();
    }
}