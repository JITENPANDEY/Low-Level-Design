package com.atmmachine.state;

import com.atmmachine.atm.ATM;
import com.atmmachine.model.Card;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthenticatedState implements ATMState {

    private final ATM atm;

    @Override
    public void insertCard(Card card, int pin) {
        System.out.println("Card already inserted and authenticated. Please proceed with your transaction.");
    }

    @Override
    public void ejectCard() {
        atm.setCurrentState(atm.getIdleState());
        atm.setCurrentCard(null);
        System.out.println("Card ejected. Thank you for using the ATM.");
    }

    @Override
    public void checkBalance() {
        if (atm.getCurrentCard() == null) {
            System.out.println("No card inserted. Please insert a card first.");
            return;
        }
        double balance = atm.getBankingService().getBalance(atm.getCurrentCard().getAccountNumber());
        System.out.println("Your current balance is: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        if (atm.getCurrentCard() == null) {
            System.out.println("No card inserted. Please insert a card first.");
            return;
        }
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount. Please enter a positive amount.");
            return;
        }
        if (atm.getBankingService().withdraw(atm.getCurrentCard().getAccountNumber(), amount)) {
            atm.getCashDispenser().dispenseCash(amount);
            System.out.println("Withdrawal successful. Amount dispensed: " + amount);
        } else {
            System.out.println("Withdrawal failed. Insufficient funds or error processing the transaction.");
        }
    }

    @Override
    public void deposit(double amount) {
        if (atm.getCurrentCard() == null) {
            System.out.println("No card inserted. Please insert a card first.");
            return;
        }
        if (amount <= 0) {
            System.out.println("Invalid deposit amount. Please enter a positive amount.");
            return;
        }
        if (atm.getBankingService().deposit(atm.getCurrentCard().getAccountNumber(), amount)) {
            atm.getCashDispenser().loadCash((int) amount);
            System.out.println("Deposit successful. Amount deposited: " + amount + " and your new balance is: " + atm.getCashDispenser().getCashAvailable());
        } else {
            System.out.println("Deposit failed. Error processing the transaction.");
        }
    }
}
