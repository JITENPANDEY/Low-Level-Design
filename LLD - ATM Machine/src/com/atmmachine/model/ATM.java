package com.atmmachine.model;

import com.atmmachine.service.AuthenticationService;
import com.atmmachine.cashdispenser.CashWithdrawProcessor;
import com.atmmachine.service.IBankingService;
import com.atmmachine.state.ATMState;
import com.atmmachine.state.IdleState;
import com.atmmachine.transaction.BalanceInquiryTransaction;
import com.atmmachine.transaction.DepositTransaction;
import com.atmmachine.transaction.Transaction;
import com.atmmachine.transaction.WithdrawalTransaction;

import java.util.EnumMap;
import java.util.Map;

public class ATM {

    private ATMState currentState;
    private Card currentCard;

    private final CashWithdrawProcessor cashWithdrawProcessor;
    private final AuthenticationService authenticationService;
    private Map<TransactionType, Transaction> transactionMap;

    public ATM(CashWithdrawProcessor cashWithdrawProcessor, AuthenticationService authenticationService, IBankingService bankingService) {
        this.currentState = new IdleState();
        this.cashWithdrawProcessor = cashWithdrawProcessor;
        this.authenticationService =  authenticationService;
        this.transactionMap = getTransactionRegisteredMap(bankingService);
    }

    private Map<TransactionType, Transaction> getTransactionRegisteredMap(IBankingService bankingService) {
        Map<TransactionType, Transaction> map = new EnumMap<>(TransactionType.class);
        map.put(TransactionType.CASH_WITHDRAWAL, new WithdrawalTransaction(bankingService));
        map.put(TransactionType.DEPOSIT, new DepositTransaction(bankingService));
        map.put(TransactionType.BALANCE_INQUIRY, new BalanceInquiryTransaction(bankingService));
        return map;
    }


    public void insertCard(Card card) {
        currentState.insertCard(this, card);
    }

    public boolean authenticatePin(String pin) {
        return currentState.authenticatePin(this, pin);
    }

    public void selectTransaction(TransactionRequestContext txnRequestContext) {
        currentState.selectTransaction(txnRequestContext);
    }

    public void ejectCard() {
        currentState.ejectCard(this);
    }

    public void setCurrentState(ATMState currentState) {
        this.currentState = currentState;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public CashWithdrawProcessor getCashWithdrawProcessor() {
        return cashWithdrawProcessor;
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }


    public Transaction getTransaction(TransactionType transactionType) {
        return transactionMap.get(transactionType);
    }
}
