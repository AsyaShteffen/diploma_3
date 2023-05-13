package ru.praktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage extends CommonButtons {
    public static final String URL_FORGOT_PASSWORD = "https://stellarburgers.nomoreparties.site/forgot-password";

    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/form/button")
    private SelenideElement restoreButton;
    @FindBy(how = How.XPATH, using = ".//a[text() = 'Войти']")
    private SelenideElement loginButton;

    public static String getURL_FORGOT_PASSWORD() {
        return URL_FORGOT_PASSWORD;
    }

    public void clickLoginButton() {
        loginButton.shouldBe(Condition.visible).click();
    }

    public void clickRestoreButton() {
        restoreButton.shouldBe(Condition.visible).click();
    }
}