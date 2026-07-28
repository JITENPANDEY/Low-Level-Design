package com.atmmachine.transaction;

import com.atmmachine.model.Account;
import com.atmmachine.model.TransactionRequestContext;
import com.atmmachine.service.IBankingService;

import java.math.BigDecimal;

public class BalanceInquiryTransaction extends Transaction {

    public BalanceInquiryTransaction(IBankingService bankingService) {
        super(bankingService);
    }

    @Override
    public void execute(TransactionRequestContext transactionRequestContext) {
        Account account = transactionRequestContext.getAtm().getCurrentCard().getAccount();
        BigDecimal balance = bankingService.getBalance(account);
        System.out.println("Your current balance is: " + balance);
    }
}
