package com.atmmachine.state;

import com.atmmachine.model.ATM;
import com.atmmachine.model.Card;
import com.atmmachine.transaction.Transaction;
import com.atmmachine.model.TransactionRequestContext;

public class SelectTransactionState extends ATMState {

    @Override
    public void insertCard(ATM atm, Card card) {
        System.out.println("A card is already inserted.");
    }

    @Override
    public boolean authenticatePin(ATM atm, String enteredPin) {
        System.out.println("User is already authenticated.");
        return true;
    }

    @Override
    public void selectTransaction(TransactionRequestContext txnRequestContext) {
        ATM atm = txnRequestContext.getAtm();
        Transaction transaction = atm.getTransaction(txnRequestContext.getTransactionType());
        transaction.execute(txnRequestContext);
    }

    @Override
    public void ejectCard(ATM atm) {
        atm.setCurrentCard(null);
        atm.setCurrentState(new IdleState());
        System.out.println("Card ejected successfully.");
    }
}
