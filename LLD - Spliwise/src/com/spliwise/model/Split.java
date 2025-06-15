package com.spliwise.model;

import lombok.Getter;
import lombok.Setter;


public abstract class Split {
    @Getter
    protected final User user;
    @Setter
    protected double amount;

    public Split(User user) {
        this.user = user;
    }

    public abstract double getAmount();
}
