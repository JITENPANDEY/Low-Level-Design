package com.spliwise.model;

import lombok.Data;
import utility.UniqueIdGenerator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
public class Group {
    private final String groupId;
    private final String groupName;
    private final List<User> userList;
    private final List<Expense> expenses;

    private final String GROUP_ID_PREFIX = "GRP";

    public Group(String groupName) {
        this.groupId = GROUP_ID_PREFIX + UniqueIdGenerator.generateId();
        this.groupName = groupName;
        this.userList = new CopyOnWriteArrayList<>();
        this.expenses = new CopyOnWriteArrayList<>();
    }

    public void addMember(User user) {
        if (user != null && !userList.contains(user)) {
            userList.add(user);
        }
    }

    public void addExpense(Expense expense) {
        if (expense != null && !expenses.contains(expense)) {
            expenses.add(expense);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Group ID       : ").append(groupId).append("\n");
        sb.append("Group Name     : ").append(groupName).append("\n");

        sb.append("Members        :\n");
        for (User user : userList) {
            sb.append("  - ").append(user.getName()).append(" (").append(user.getUserId()).append(")\n");
        }

        sb.append("Expenses       :\n");
        if (expenses.isEmpty()) {
            sb.append("  No expenses recorded yet.\n");
        } else {
            for (Expense expense : expenses) {
                sb.append("  * ").append(expense.getDescription())
                        .append(" | Amount: ₹").append(String.format("%.2f", expense.getTotalAmount()))
                        .append(" | Paid by: ").append(expense.getPaidBy().getName())
                        .append(" | Type: ").append(expense.getSplitType()).append("\n");
            }
        }

        return sb.toString();
    }
}
