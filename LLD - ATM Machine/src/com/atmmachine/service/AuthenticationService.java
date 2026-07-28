package com.atmmachine.service;

import com.atmmachine.model.Card;

public interface AuthenticationService {
    boolean authenticate(Card card, String enteredPin);
}
