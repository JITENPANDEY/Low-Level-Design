package com.spliwise.model;

public class EqualSplit extends Split {

    public EqualSplit(User user) {
        super(user);
    }

    @Override
    public double getAmount() {
        return amount;
    }
}
