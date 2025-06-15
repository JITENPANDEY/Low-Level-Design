package com.atmmachine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Account {
    private String accountNumber;
    private double balance;

    public synchronized void credit(double amount) {
        this.balance += amount;
    }

    public synchronized boolean debit(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
