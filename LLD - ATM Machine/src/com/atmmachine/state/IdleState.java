package com.atmmachine.state;

import com.atmmachine.model.ATM;
import com.atmmachine.model.Card;

public class IdleState extends ATMState {

    @Override
    public void insertCard(ATM atm, Card card) {

        atm.setCurrentCard(card);

        atm.setCurrentState(new HasCardState());

        System.out.println("Card inserted successfully.");
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("No card inserted.");
    }
}
