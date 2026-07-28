package com.atmmachine.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class TransactionRequestContext {
    private ATM atm;
    private BigDecimal amount;
    private TransactionType transactionType;
}
