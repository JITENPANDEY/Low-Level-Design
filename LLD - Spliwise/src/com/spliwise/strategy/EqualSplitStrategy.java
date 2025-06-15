package com.spliwise.strategy;

import com.spliwise.model.EqualSplit;
import com.spliwise.model.Split;

import java.util.List;

public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public void validateAndSplit(double totalAmount, List<Split> splits) {
        if (splits == null || splits.isEmpty()) {
            throw new IllegalArgumentException("Splits cannot be null or empty");
        }

        double splitAmount = totalAmount / splits.size();

        for (Split split : splits) {
            if(!(split instanceof EqualSplit)) {
                throw new IllegalArgumentException("All splits must be of type EqualSplit");
            }
            split.setAmount(splitAmount);
        }
    }
}
