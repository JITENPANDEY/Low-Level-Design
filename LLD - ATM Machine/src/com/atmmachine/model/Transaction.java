package com.atmmachine.model;

import com.atmmachine.service.IBankingService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public abstract class Transaction {
    protected final String accountNumber;
    protected final double amount;

    public abstract boolean execute(IBankingService bankingService);
}
