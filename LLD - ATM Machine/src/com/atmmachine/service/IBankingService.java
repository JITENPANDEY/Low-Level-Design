package com.atmmachine.service;

import com.atmmachine.model.Account;
import com.atmmachine.model.Card;

public interface IBankingService {
    void addAccount(Account account);
    boolean authenticate(Card card, int pin);
    Account getAccount(String accountNumber);
    boolean withdraw(String accountNumber, double amount);
    boolean deposit(String accountNumber, double amount);
    double getBalance(String accountNumber);
}
