package com.vendingmachine.dto;

public enum Note implements Currency {
    TEN(10.0),TWENTY(20.0), FIFTY(50.0), HUNDRED(100.0);
    private final double value;
    Note(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return this.value;
    }
}
