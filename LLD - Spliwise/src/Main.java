import com.spliwise.enums.SplitType;
import com.spliwise.factory.ExpenseFactory;
import com.spliwise.model.EqualSplit;
import com.spliwise.model.Expense;
import com.spliwise.model.Group;
import com.spliwise.model.PercentageSplit;
import com.spliwise.model.User;
import com.spliwise.service.ExpenseManager;
import utility.UniqueIdGenerator;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        for (int i = 0; i <9999 ; i++) {
            System.out.println(UniqueIdGenerator.generateId());
        }

//        //create users
//        User user1 = new User("Jiten", "jiten@mail.com", "1234567890");
//        User user2 = new User("Aman", "aman@gmail.com", "6367122232");
//        User user3 = new User("Ritik", "ritik@gmail.com", "6457132132");
//
//        ExpenseManager expenseManager = ExpenseManager.getInstance();
//
//        //add users to the service
//        expenseManager.addUser(user1);
//        expenseManager.addUser(user2);
//        expenseManager.addUser(user3);
//
//        Expense expense = ExpenseFactory.createExpense(
//                1000, "Dinner at Restaurant", user1, SplitType.PERCENTAGE,
//                List.of(new PercentageSplit(user1, 20), new PercentageSplit(user2, 70), new PercentageSplit(user3, 10))
//        );
//
//        expenseManager.addExpense(expense);
//        expenseManager.showBalances();
//
//        // Example: settle balance between user1 and user2
//        expenseManager.settleBalance(user1, user2);
//        expenseManager.settleBalance(user2, user3);
//        expenseManager.settleBalance(user3, user1);
//
//
//        System.out.println("----------------------------------");
//
//        //create group expense
//        Group group = new Group("Friends Trip");
//        group.addMember(user1);
//        group.addMember(user2);
//        group.addMember(user3);
//        expenseManager.addGroup(group);
//        System.out.println("Group created:\n" + group);
//
//        Expense groupExpense = ExpenseFactory.createExpense(
//                1500, "Trip Expenses", user1, SplitType.EQUAL,
//                List.of(new EqualSplit(user1), new EqualSplit(user2), new EqualSplit(user3))
//        );
//
//        expenseManager.addExpenseToGroup(group.getGroupId(), groupExpense);
//        // Print the expenses for the group
//        System.out.println("Expenses for group: " + group.getGroupName());
//        expenseManager.showBalances();




    }
}