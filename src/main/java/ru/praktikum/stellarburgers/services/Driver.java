package ru.praktikum.stellarburgers.services;

public class Driver {
    private final String chromeDriverAbsolutePath = "/Users/18958940/Desktop/Учеба/diploma/diploma_3/src/main/resources/chromedriver";
    private final String yandexDriverAbsolutePath = "/Users/18958940/Desktop/Учеба/diploma/diploma_3/src/main/resources/yandexdriver";

    public String getDriverPath() {
        String browserName = getBrowser();

        if (browserName.equalsIgnoreCase("chrome"))
            return chromeDriverAbsolutePath;

        if (browserName.equalsIgnoreCase("yandex"))
            return yandexDriverAbsolutePath;

        return chromeDriverAbsolutePath;
    }

    private String getBrowser() {
        String value = System.getProperty("browser");
        if (value == null || value.isEmpty())
            return "chrome";

        return value;
    }
}