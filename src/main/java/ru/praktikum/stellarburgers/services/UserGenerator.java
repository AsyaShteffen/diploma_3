package ru.praktikum.stellarburgers.services;
import ru.praktikum.stellarburgers.model.User;

import com.github.javafaker.Faker;

public class UserGenerator {
    private static final Faker faker = new Faker();

    public static User random() {
        return User.builder()
                .name(faker.name().firstName())
                .password(faker.internet().password(6, 10))
                .email(faker.internet().emailAddress())
                .build();
    }
}
