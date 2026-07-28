package com.atmmachine.transaction;

import com.atmmachine.model.ATM;
import com.atmmachine.model.Account;
import com.atmmachine.model.TransactionRequestContext;
import com.atmmachine.service.IBankingService;

import java.math.BigDecimal;

public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(IBankingService bankingService) {
        super(bankingService);
    }

    @Override
    public void execute(TransactionRequestContext transactionRequestContext) {
        ATM atm = transactionRequestContext.getAtm();
        Account account = atm.getCurrentCard().getAccount();
        BigDecimal amount = transactionRequestContext.getAmount();
        bankingService.withdraw(account, amount);
        atm.getCashWithdrawProcessor().dispense(amount);
        System.out.println("Please collect your cash.");
    }
}
