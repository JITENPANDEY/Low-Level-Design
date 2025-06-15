package com.atmmachine.state;

import com.atmmachine.atm.ATM;
import com.atmmachine.model.Card;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class IdleState implements ATMState{
    private final ATM atm;

    @Override
    public void insertCard(Card card, int pin) {
        boolean isAuthenticated = atm.getBankingService().authenticate(card, pin);
        if (!isAuthenticated) {
            throw new RuntimeException("Authentication failed. Please check your card and PIN.");
        }
        atm.setCurrentCard(card);
        atm.setCurrentState(atm.getAuthenticatedState());
        System.out.println("Card authenticated successfully.");
    }

    @Override
    public void ejectCard() {
        System.out.println("Please insert a card first..!");
    }

    @Override
    public void checkBalance() {
        System.out.println("Please insert a card first..!");
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Please insert a card first..!");
    }

    @Override
    public void deposit(double amount) {
        System.out.println("Please insert a card first..!");
    }
}
