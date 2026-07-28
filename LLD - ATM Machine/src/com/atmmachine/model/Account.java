package com.atmmachine.model;

import java.math.BigDecimal;

public class Account {
    private String accountNumber;
    private BigDecimal balance;

    public Account(String accountNumber, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void credit(BigDecimal amount) {
        this.balance.subtract(amount);
    }

    /** Why debit() inside Account?
     - Because Account owns balance.
     - Account ke bahar koi balance manipulate nahi karega.
     - Ye encapsulation hai.
     */
    public boolean debit(BigDecimal amount) {
        if(amount.compareTo(balance) <= 0) {
            balance = balance.subtract(amount);
            return true;
        }
        return false;
    }

}
