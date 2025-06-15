package com.spliwise.strategy;

import com.spliwise.model.PercentageSplit;
import com.spliwise.model.Split;

import java.util.List;

public class PercentageSplitStrategy implements SplitStrategy {

    @Override
    public void validateAndSplit(double totalAmount, List<Split> splits) {
        if (splits == null || splits.isEmpty()) {
            throw new IllegalArgumentException("Splits cannot be null or empty");
        }

        double totalPercentage = 0.0;
        for (Split split : splits) {
            if(!(split instanceof PercentageSplit)) {
                throw new IllegalArgumentException("All splits must be of type PercentageSplit");
            }
            totalPercentage += ((PercentageSplit) split).getPercentage();
        }

        if (totalPercentage != 100.0) {
            throw new IllegalArgumentException("Total percentage must equal 100%");
        }

        for (Split split : splits) {
            double percentage = ((PercentageSplit) split).getPercentage();
            double amount = (percentage / 100) * totalAmount;
            split.setAmount(amount);
        }
    }
}
