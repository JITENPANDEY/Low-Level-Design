package com.spliwise.strategy;

import com.spliwise.model.ExactSplit;
import com.spliwise.model.Split;

import java.util.List;

public class ExactSplitStrategy implements SplitStrategy {

    @Override
    public void validateAndSplit(double totalAmount, List<Split> splits) {
        if (splits == null || splits.isEmpty()) {
            throw new IllegalArgumentException("Splits cannot be null or empty");
        }

        double totalSplitAmount = 0.0;
        for (Split split : splits) {
            if(!(split instanceof ExactSplit)) {
                throw new IllegalArgumentException("All splits must be of type ExactSplit");
            }
            totalSplitAmount += split.getAmount();
        }

        if (totalSplitAmount != totalAmount) {
            throw new IllegalArgumentException("Total split amount does not match the total amount");
        }
    }
}
