package com.atmmachine.state;

import com.atmmachine.model.ATM;
import com.atmmachine.model.Card;
import com.atmmachine.model.TransactionRequestContext;

public abstract class ATMState {

    /**
     * Insert a card into the ATM.
     */
    public void insertCard(ATM atm, Card card) {
        invalidOperation();
    }

    /**
     * Authenticate the entered PIN.
     */
    public boolean authenticatePin(ATM atm, String enteredPin) {
        return false;
    }

    /**
     * User selects an operation.
     */
    public void selectTransaction(TransactionRequestContext txnRequestContext) {
        invalidOperation();
    }

    /**
     * Eject the current card.
     */
    public void ejectCard(ATM atm) {
        invalidOperation();
    }

    protected void invalidOperation() {
        throw new IllegalStateException("Operation is not allowed in current state.");
    }
}