package com.atmmachine.transaction;

import com.atmmachine.model.TransactionRequestContext;
import com.atmmachine.service.IBankingService;

public abstract class Transaction {

    protected final IBankingService bankingService;

    protected Transaction(IBankingService bankingService) {
        this.bankingService = bankingService;
    }

    public abstract void execute(TransactionRequestContext transactionRequestContext);
}
