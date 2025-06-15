package com.atmmachine.model;

import com.atmmachine.service.IBankingService;

public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(String accountNumber, double amount) {
        super(accountNumber, amount);
    }

    @Override
    public boolean execute(IBankingService bankingService) {
        return bankingService.withdraw(accountNumber, amount);
    }
}
