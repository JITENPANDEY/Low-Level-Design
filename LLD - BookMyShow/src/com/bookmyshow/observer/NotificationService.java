package com.bookmyshow.observer;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private final List<NotificationObserver> observers = new ArrayList<>();

    public void registerObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (NotificationObserver observer : observers) {
            observer.notify(message);
        }
    }
}
