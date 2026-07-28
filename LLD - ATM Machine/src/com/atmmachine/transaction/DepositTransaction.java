package com.atmmachine.transaction;

import com.atmmachine.model.Account;
import com.atmmachine.model.TransactionRequestContext;
import com.atmmachine.service.IBankingService;


public class DepositTransaction extends Transaction {

    public DepositTransaction(IBankingService bankingService) {
        super(bankingService);
    }

    @Override
    public void execute(TransactionRequestContext transactionRequestContext) {
        Account account = transactionRequestContext.getAtm().getCurrentCard().getAccount();
        bankingService.deposit(account, transactionRequestContext.getAmount());
        System.out.println("₹" + account.getBalance() + " Amount deposited successfully.");
    }
}
