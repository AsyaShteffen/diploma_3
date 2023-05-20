package ru.praktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class CommonButtons {
    // кнопка-логотип
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement logoButton;

    // кнопка "личный кабинет"
    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    private SelenideElement personalAreaButton;

    // кнопка "конструктор"
    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    private SelenideElement constructorButton;

    @Step("Клик на кнопку-личный кабинет")
    public void clickPersonalAreaButton() {
        personalAreaButton.shouldBe(Condition.visible).click();
    }

    @Step("Клик на кнопку-конструктор")
    public void clickConstructorButton() {
        constructorButton.shouldBe(Condition.visible).click();
    }
}
