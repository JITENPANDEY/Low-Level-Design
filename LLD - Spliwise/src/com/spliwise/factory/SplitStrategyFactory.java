package com.spliwise.factory;

import com.spliwise.enums.SplitType;
import com.spliwise.strategy.EqualSplitStrategy;
import com.spliwise.strategy.ExactSplitStrategy;
import com.spliwise.strategy.PercentageSplitStrategy;
import com.spliwise.strategy.SplitStrategy;

public class SplitStrategyFactory {
    public static SplitStrategy getSplitStrategy(SplitType expenseType) {
        return switch (expenseType) {
            case EQUAL -> new EqualSplitStrategy();
            case EXACT -> new ExactSplitStrategy();
            case PERCENTAGE -> new PercentageSplitStrategy();
        };
    }
}
