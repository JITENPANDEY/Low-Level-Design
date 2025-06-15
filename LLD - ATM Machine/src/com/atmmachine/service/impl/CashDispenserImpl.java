package com.atmmachine.service.impl;

import com.atmmachine.service.ICashDispenser;

import java.util.concurrent.atomic.AtomicInteger;

public class CashDispenserImpl implements ICashDispenser {

    private final AtomicInteger cashAvailable;

    public CashDispenserImpl(int cashAvailable) {
        this.cashAvailable = new AtomicInteger(cashAvailable);
    }

    @Override
    public void dispenseCash(double amount) {
        if (amount <= 0 || amount > cashAvailable.get()) {
            return;
        }
        int newBalance = cashAvailable.addAndGet((int) -amount);
    }

    @Override
    public void loadCash(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to load must be positive");
        }
        cashAvailable.addAndGet(amount);
    }

    @Override
    public int getCashAvailable() {
        return cashAvailable.get();
    }
}
