package ru.netology.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {

    private static final Faker FAKER = new Faker(new Locale("en"));

    private DataGenerator() {

    }

    //метод для генерации логина
    public static String getRandomLogin() {
        return FAKER.name().username();
    }

    //метод для генерации пароль
    public static String getRandomPassword() {
        return FAKER.internet().password();
    }

    //можно отдельно сделать класс Registration (невложенный)
    public static class Registration {
        private Registration() {
        }

        //метод, который генерирует незарегистрированного пользователя (генерирует и возвращает пользователя)
        //собирает логин, пароль, статус в пользователя
        public static RegistrationDto getUser(String status) {
            return new RegistrationDto(getRandomLogin(), getRandomPassword(), status);
        }

        //метод, который генерирует зарегистрированного пользователя
        public static RegistrationDto getRegisteredUser(String status) {
            return ApiHelper.sendRequest(getUser(status));
        }
    }

    // имена полей должны совпадать с JSON
    @Value
    public static class RegistrationDto {
        String login;
        String password;
        String status;
    }
}