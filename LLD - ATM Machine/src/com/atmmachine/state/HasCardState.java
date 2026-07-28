package com.atmmachine.state;

import com.atmmachine.model.ATM;
import com.atmmachine.model.Card;
import com.atmmachine.service.AuthenticationService;

public class HasCardState extends ATMState {

    @Override
    public void insertCard(ATM atm, Card card) {
        System.out.println("A card is already inserted.");
    }

    @Override
    public boolean authenticatePin(ATM atm, String enteredPin) {

        AuthenticationService authenticationService = atm.getAuthenticationService();

        Card currentCard = atm.getCurrentCard();

        if (authenticationService.authenticate(currentCard, enteredPin)) {
            atm.setCurrentState(new SelectTransactionState());
            System.out.println("PIN verified successfully.");
            return true;
        } else {
            System.out.println("Invalid PIN.");
            return false;
        }
    }

    @Override
    public void ejectCard(ATM atm) {

        atm.setCurrentCard(null);

        atm.setCurrentState(new IdleState());

        System.out.println("Card ejected successfully.");
    }
}
