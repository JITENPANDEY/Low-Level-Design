package com.spliwise.factory;

import com.spliwise.enums.SplitType;
import com.spliwise.model.Expense;
import com.spliwise.model.Split;
import com.spliwise.model.User;

import java.util.List;

public class ExpenseFactory {
    public static Expense createExpense(double totalAmount, String description, User paidBy, SplitType splitType, List<Split> splits) {
        SplitStrategyFactory.getSplitStrategy(splitType).validateAndSplit(totalAmount, splits);
        Expense expense = new Expense(totalAmount, description, paidBy, splitType, splits);
        System.out.println("Expense created: \n" + expense);
        return expense;
    }
}
