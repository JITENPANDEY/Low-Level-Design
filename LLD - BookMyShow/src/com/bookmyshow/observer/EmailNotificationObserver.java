package com.bookmyshow.observer;

public class EmailNotificationObserver implements NotificationObserver {
    private final String email;

    public EmailNotificationObserver(String email) {
        this.email = email;
    }
    @Override
    public void notify(String message) {
        // Logic to send email notification
        System.out.println("Email Notification To : " + email + " message : " + message);
    }
}
