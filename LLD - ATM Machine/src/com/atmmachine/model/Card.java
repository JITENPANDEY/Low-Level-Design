package com.atmmachine.model;

import java.time.LocalDate;

public class Card {
    private String cardNumber;
    // For LLD simplicity.
    // In production this should be a hash.
    private String pin;
    private LocalDate expiryDate;
    private Account account;

    public Card(String cardNumber, String pin, LocalDate expiryDate, Account account) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.expiryDate = expiryDate;
        this.account = account;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public Account getAccount() {
        return account;
    }
}
