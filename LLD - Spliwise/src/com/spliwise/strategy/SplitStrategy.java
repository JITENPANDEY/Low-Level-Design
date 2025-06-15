package com.spliwise.strategy;

import com.spliwise.model.Split;

import java.util.List;

public interface SplitStrategy {
    void validateAndSplit(double totalAmount, List<Split> splits);
}
