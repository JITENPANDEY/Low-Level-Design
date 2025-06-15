package com.atmmachine.service;

public interface ICashDispenser {
    void dispenseCash(double amount);
    void loadCash(int amount);
    int getCashAvailable();
}
