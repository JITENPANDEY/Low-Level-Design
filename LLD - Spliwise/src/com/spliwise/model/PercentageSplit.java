package com.spliwise.model;


import lombok.Getter;

@Getter
public class PercentageSplit extends Split{
    private final double percentage;

    public PercentageSplit(User user, double percentage) {
        super(user);
        this.percentage = percentage;
    }
    @Override
    public double getAmount() {
        return amount;
    }
}
