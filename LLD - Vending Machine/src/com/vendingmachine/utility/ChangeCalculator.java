package com.vendingmachine.utility;

import com.vendingmachine.dto.Coin;
import com.vendingmachine.dto.Currency;
import com.vendingmachine.dto.Note;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ChangeCalculator {
    private static final List<Double> denominations = List.of(
            100.0, 50.0, 20.0, 10.0, 5.0, 2.0, 1.0
    );

    public static List<Currency> getChange(double changeAmount) {
        List<Currency> change = new ArrayList<>();

        for (double denom : denominations) {
            while (changeAmount >= denom) {
                changeAmount = Math.round((changeAmount - denom) * 100.0) / 100.0;
                change.add(findCurrencyByValue(denom));
            }
        }

        if (changeAmount > 0) {
            throw new IllegalStateException("Cannot provide exact change.");
        }

        return change;
    }

    private static Currency findCurrencyByValue(double value) {
        for (Coin c : Coin.values()) {
            if (Double.compare(c.getValue(), value) == 0) {
                return c;
            }
        }
        for (Note n : Note.values()) {
            if (Double.compare(n.getValue(), value) == 0) {
                return n;
            }
        }
        throw new IllegalArgumentException("Unknown currency denomination: " + value);
    }
}

