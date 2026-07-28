package com.atmmachine.service.impl;

import com.atmmachine.model.Account;
import com.atmmachine.service.IBankingService;

import java.math.BigDecimal;

/**
 * Implementation of the BankingService interface that manages accounts and transactions.
 * This service allows adding accounts, authenticating cards, and performing deposits and withdrawals.
 */
public class BankingServiceImpl implements IBankingService {

    public void withdraw(Account account, BigDecimal amount) {
        account.debit(amount);
    }

    public void deposit(Account account, BigDecimal amount) {
        account.credit(amount);
    }

    public BigDecimal getBalance(Account account) {
        return account.getBalance();
    }
}
