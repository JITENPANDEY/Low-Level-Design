package com.spliwise.service;

import com.spliwise.factory.SplitStrategyFactory;
import com.spliwise.model.Expense;
import com.spliwise.model.Group;
import com.spliwise.model.Split;
import com.spliwise.model.User;
import com.spliwise.strategy.SplitStrategy;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class ExpenseManager {
    private static ExpenseManager instance;
    private final Map<String, User> users;
    private final Map<String, Group> groups;
    private final Map<User, Map<User, Double>> balanceSheet;

    private ExpenseManager() {
        this.users = new ConcurrentHashMap<>();
        this.groups = new ConcurrentHashMap<>();
        this.balanceSheet = new ConcurrentHashMap<>();
    }

    public static synchronized ExpenseManager getInstance() {
        if (instance == null) {
            synchronized (ExpenseManager.class) {
                if (instance == null) {
                    instance = new ExpenseManager();
                }
            }
        }
        return instance;
    }

    public void addUser(User user) {
        if (user != null && !users.containsKey(user.getUserId())) {
            users.put(user.getUserId(), user);
        }
    }

    public void addGroup(Group group) {
        if (group != null && !groups.containsKey(group.getGroupId())) {
            groups.put(group.getGroupId(), group);
        }
    }

    public void addExpense(Expense expense) {
        if (expense == null || expense.getPaidBy() == null) {
            throw new IllegalArgumentException("Expense or paid by user cannot be null.");
        }

        SplitStrategy splitStrategy = SplitStrategyFactory.getSplitStrategy(expense.getSplitType());
        splitStrategy.validateAndSplit(expense.getTotalAmount(), expense.getSplits());

        balanceSheet.putIfAbsent(expense.getPaidBy(), new HashMap<>());
        for (Split split : expense.getSplits()) {
            User otherUser = split.getUser();
            if (!otherUser.equals(expense.getPaidBy())) {
                balanceSheet.putIfAbsent(otherUser, new HashMap<>());
                balanceSheet.get(otherUser).put(expense.getPaidBy(), balanceSheet.get(otherUser).getOrDefault(expense.getPaidBy(), 0.0) + split.getAmount());
                balanceSheet.get(expense.getPaidBy()).put(otherUser, balanceSheet.get(expense.getPaidBy()).getOrDefault(otherUser, 0.0) - split.getAmount());
            }
        }
    }

    public void addExpenseToGroup(String groupId, Expense expense) {
        Group group = groups.get(groupId);
        if (group != null) {
            group.addExpense(expense);
            addExpense(expense);
        } else {
            throw new IllegalArgumentException("Group with ID " + groupId + " does not exist.");
        }
    }

    public void showBalances() {
//        List<User> userList = new ArrayList<>(users.values());
//
//        System.out.printf("%-15s", "User");
//        for (User col : userList) {
//            System.out.printf("%-15s", col.getName());
//        }
//        System.out.println();
//
//        for (User row : userList) {
//            System.out.printf("%-15s", row.getName());
//            for (User col : userList) {
//                double amount = balanceSheet
//                        .getOrDefault(row, new HashMap<>())
//                        .getOrDefault(col, 0.0);
//                System.out.printf("%-15.2f", amount);
//            }
//            System.out.println();
//        }
        for (User u1 : balanceSheet.keySet()) {
            for (User u2 : balanceSheet.get(u1).keySet()) {
                double amount = balanceSheet.get(u1).get(u2);
                if (amount > 0) {
                    System.out.printf("%s owes %.2f to %s%n", u1.getName(), amount, u2.getName());
                }
            }
        }
    }

    public void settleBalance(User user1, User user2) {
        if (!balanceSheet.containsKey(user1) || !balanceSheet.get(user1).containsKey(user2)) {
            System.out.println("No balance to settle between " + user1.getName() + " and " + user2.getName());
            return;
        }

        double amount = balanceSheet.get(user1).get(user2);
        if (amount == 0.0) {
            System.out.println("No outstanding balance between " + user1.getName() + " and " + user2.getName());
            return;
        }

        // Update the balance sheet
        balanceSheet.get(user1).put(user2, 0.0);
        balanceSheet.get(user2).put(user1, 0.0);

        String message = amount > 0
                ? String.format("%s paid ₹%.2f to %s. Balance settled.", user1.getName(), amount, user2.getName())
                : String.format("%s paid ₹%.2f to %s. Balance settled.", user2.getName(), -amount, user1.getName());

        System.out.println(message);

    }

}
