package com.spliwise.model;

import lombok.Getter;
import lombok.ToString;
import utility.UniqueIdGenerator;

@ToString
@Getter
public class User {
    private final String userId;
    private final String name;
    private final String email;
    private final String phoneNumber;

    private final String USER_ID_PREFIX = "USER";

    public User(String name, String email, String phoneNumber) {
        this.userId = USER_ID_PREFIX + UniqueIdGenerator.generateId(); // Generate a unique user ID
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
