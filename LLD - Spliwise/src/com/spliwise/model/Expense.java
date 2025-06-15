package com.spliwise.model;

import com.spliwise.enums.SplitType;
import lombok.Getter;
import utility.UniqueIdGenerator;

import java.util.List;

@Getter
public class Expense {
    private  final String expenseId;
    private final String description;
    private final double totalAmount;
    private final User paidBy;
    private final SplitType splitType;
    private final List<Split> splits;

    private final String EXPENSE_ID_PREFIX = "EXP";

    public Expense(double totalAmount, String description, User paidBy, SplitType expenseType, List<Split> splits) {
        this.expenseId = EXPENSE_ID_PREFIX + UniqueIdGenerator.generateId();
        this.description = description;
        this.totalAmount = totalAmount;
        this.paidBy = paidBy;
        this.splitType = expenseType;
        this.splits = splits;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Expense ID     : ").append(expenseId).append("\n");
        sb.append("Description    : ").append(description).append("\n");
        sb.append("Total Amount   : ₹").append(String.format("%.2f", totalAmount)).append("\n");
        sb.append("Paid By        : ").append(paidBy.getName()).append("\n");
        sb.append("Split Type     : ").append(splitType).append("\n");
        sb.append("Participants   :\n");

        sb.append(String.format("%-15s %-15s\n", "User", "Amount"));
        sb.append("----------------------------------\n");

        for (Split split : splits) {
            sb.append(String.format("%-15s ₹%-14.2f\n", split.getUser().getName(), split.getAmount()));
        }

        return sb.toString();
    }
}
