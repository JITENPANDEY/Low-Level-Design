package com.bookmyshow.model;

import com.bookmyshow.observer.EmailNotificationObserver;
import com.bookmyshow.observer.NotificationObserver;
import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private String userId;
    private String name;
    private String email;
    private final NotificationObserver notificationObserver;

    public User(String name, String email) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.notificationObserver = new EmailNotificationObserver(email);
    }
}
