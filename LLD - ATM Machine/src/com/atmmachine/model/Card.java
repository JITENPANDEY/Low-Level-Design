package com.atmmachine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Card {
    private final String cardNumber;
    private final int pin;
    private final String accountNumber;
}
