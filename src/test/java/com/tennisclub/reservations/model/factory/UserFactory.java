package com.tennisclub.reservations.model.factory;

import com.tennisclub.reservations.dto.UserDto;
import com.tennisclub.reservations.dto.create.UserCreateDto;
import com.tennisclub.reservations.model.User;

import java.util.ArrayList;

public class UserFactory {

    private static final String DEFAULT_NAME = "jj";
    private static final String DEFAULT_PHONE_NUMBER = "+44 20 7946 0958";
    private static final String DEFAULT_PASSWORD = "password";

    public static User createUser() {
        return new User(DEFAULT_NAME, DEFAULT_PHONE_NUMBER, DEFAULT_PASSWORD, null);
    }

    public static User createUser(String name) {
        return new User(name, DEFAULT_PHONE_NUMBER, DEFAULT_PASSWORD, null);
    }

    public static User createUser(String name, String phoneNumber) {
        return new User(name, phoneNumber, DEFAULT_PASSWORD, new ArrayList<>());
    }

    public static UserDto createDto() {
        return new UserDto(DEFAULT_NAME, DEFAULT_PHONE_NUMBER, DEFAULT_PASSWORD);
    }

    public static UserDto createDto(Long id, String phoneNumber) {
        var user = new UserDto(DEFAULT_NAME, phoneNumber, DEFAULT_PASSWORD);
        user.setId(id);
        return user;
    }

    public static UserCreateDto createCreateDto() {
        return new UserCreateDto(DEFAULT_NAME, DEFAULT_PHONE_NUMBER, DEFAULT_PASSWORD);
    }

    public static UserCreateDto createCreateDto(String name) {
        return new UserCreateDto(name, DEFAULT_PHONE_NUMBER, DEFAULT_PASSWORD);
    }

    public static UserCreateDto createCreateDto(String name, String phoneNumber) {
        return new UserCreateDto(name, phoneNumber, DEFAULT_PASSWORD);
    }
}
