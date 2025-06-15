package com.atmmachine.atm;

import com.atmmachine.model.Card;
import com.atmmachine.service.IBankingService;
import com.atmmachine.service.ICashDispenser;
import com.atmmachine.state.ATMState;
import com.atmmachine.state.AuthenticatedState;
import com.atmmachine.state.IdleState;
import lombok.Data;

@Data
public class ATM {
    private final IBankingService bankingService;
    private final ICashDispenser cashDispenser;

    private final ATMState idleState;
    private final ATMState authenticatedState;

    private ATMState currentState;
    private Card currentCard;

    public ATM(IBankingService bankingService, ICashDispenser cashDispenser) {
        this.bankingService = bankingService;
        this.cashDispenser = cashDispenser;
        this.idleState = new IdleState(this);
        this.authenticatedState = new AuthenticatedState(this);
        this.currentState = idleState; // Start in idle state
    }

    public void insertCard(Card card, int pin) {
        currentState.insertCard(card, pin);
    }
    public void ejectCard() {
        currentState.ejectCard();
    }
    public void checkBalance() {
        currentState.checkBalance();
    }
    public void withdraw(double amount) {
        currentState.withdraw(amount);
    }
    public void deposit(double amount) {
        currentState.deposit(amount);
    }


}
