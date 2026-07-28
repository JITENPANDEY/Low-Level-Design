package com.atmmachine.service.impl;

import com.atmmachine.model.Card;
import com.atmmachine.service.AuthenticationService;

public class AuthenticateServiceImpl implements AuthenticationService {
    @Override
    public boolean authenticate(Card card, String enteredPin) {
        return card.getPin().equals(enteredPin);
    }
}
