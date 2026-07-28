package com.atmmachine.service;

import com.atmmachine.model.Account;

import java.math.BigDecimal;

public interface IBankingService {
    void withdraw(Account account, BigDecimal amount);

    void deposit(Account account, BigDecimal amount);

    BigDecimal getBalance(Account account);
}
