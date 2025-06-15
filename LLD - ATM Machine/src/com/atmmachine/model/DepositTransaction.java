package com.atmmachine.model;

import com.atmmachine.service.IBankingService;

public class DepositTransaction extends Transaction {

    public DepositTransaction(String accountNumber, double amount) {
        super(accountNumber, amount);
    }

    @Override
    public boolean execute(IBankingService bankingService) {
        return bankingService.deposit(accountNumber, amount);
    }
}
