package com.atmmachine.service.impl;

import com.atmmachine.model.Account;
import com.atmmachine.model.Card;
import com.atmmachine.service.IBankingService;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of the BankingService interface that manages accounts and transactions.
 * This service allows adding accounts, authenticating cards, and performing deposits and withdrawals.
 */
public class BankingServiceImpl implements IBankingService {

    private final ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();

    @Override
    public void addAccount(Account account) {
        accounts.putIfAbsent(account.getAccountNumber(), account);
    }

    @Override
    public boolean authenticate(Card card, int pin) {
        Account account = accounts.get(card.getAccountNumber());
        return account != null && card.getPin() == pin;
    }

    @Override
    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    @Override
    public boolean withdraw(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            return account.debit(amount);
        }
        return false;
    }

    @Override
    public boolean deposit(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.credit(amount);
            return true;
        }
        return false;
    }

    @Override
    public double getBalance(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + accountNumber);
        }
        return account.getBalance();
    }
}
