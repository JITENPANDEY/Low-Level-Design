package com.vendingmachine.dto;

public enum Coin implements Currency {
    ONE(1.0), TWO(2.0), FIVE(5.0), TEN(10.0);

    private final double value;
    Coin(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return this.value;
    }
}
