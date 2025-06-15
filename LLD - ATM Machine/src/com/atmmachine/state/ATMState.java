package com.atmmachine.state;

import com.atmmachine.model.Card;

public interface ATMState {
    void insertCard(Card card, int pin);
    void ejectCard();
    void checkBalance();
    void withdraw(double amount);
    void deposit(double amount);
}
