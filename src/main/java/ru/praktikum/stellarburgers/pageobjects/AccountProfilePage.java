package ru.praktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountProfilePage extends CommonButtons {
    public static final String URL_PROF = MainPage.URL_BASE + "account/profile";

    // кнопка выхода
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement exitButton;


    @Step("Выйти из кабинета")
    public void clickExitButton() {
        exitButton.shouldBe(Condition.visible).click();
    }
}