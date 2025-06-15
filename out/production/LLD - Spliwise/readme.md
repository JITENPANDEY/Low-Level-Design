
# 🧾 Splitwise Low-Level Design (LLD)

A clean, object-oriented implementation of the **Splitwise** system demonstrating core LLD concepts, design patterns, and SOLID principles.

---

## 📌 Functional Requirements

✅ Create and manage users

✅ Create expenses and split them among users

✅ Support various split types:

* Equal
* Exact
* Percentage

✅ Track balances between users

✅ Support groups with multiple users and expenses

✅ Settle up debts

✅ Show who owes how much to whom

---

## 🧱 Core Components

| Class            | Responsibility                         |
| ---------------- | -------------------------------------- |
| `User`           | Represents a person using the system   |
| `Expense`        | Represents an expense made by a user   |
| `Split`          | Represents a share of an expense       |
| `Group`          | Logical grouping of users and expenses |
| `ExpenseManager` | Handles users, expenses, and balances  |

---

## 🎯 Design Patterns Used

| Pattern                    | Purpose                                                                        |
| -------------------------- | ------------------------------------------------------------------------------ |
| **Strategy**               | To support various splitting strategies dynamically (equal, exact, percentage) |
| **Factory**                | To instantiate appropriate split strategy at runtime                           |
| **Composite** *(optional)* | To support uniform treatment of group vs individual expenses                   |
| **Encapsulation & OOP**    | To modularize and protect state                                                |

---

## 🗂️ Class Diagram (Overview)

```plaintext
+-------------------+        +----------------------+
|     User          |        |     Expense          |
|-------------------|        |----------------------|
| id                |<>----->| id                   |
| name              |        | amount               |
| email             |        | paidBy: User         |
| mobileNumber      |        | splitStrategy        |
+-------------------+        | List<Split> splits   |
                             +----------------------+
                                      |
                                      ▼
                           +------------------------+
                           |      Split (abstract)  |
                           |------------------------|
                           | user: User             |
                           | amount                 |
                           +------------------------+
                             ▲         ▲         ▲
                             |         |         |
           +----------------+     +--------------+------------------+
           | EqualSplit     |     | ExactSplit                      |
           +----------------+     +--------------+                  |
                                    | PercentageSplit               |
                                    +-------------------------------+

+-------------------------+     +----------------------------+
| SplitStrategy (interface)|<----| EqualSplitStrategy         |
+-------------------------+     | ExactSplitStrategy         |
| calculateSplits()       |     | PercentageSplitStrategy    |
+-------------------------+     +----------------------------+

+---------------------------+
| Group                     |
|---------------------------|
| id, name                  |
| users: List<User>         |
| expenses: List<Expense>   |
+---------------------------+

+------------------------------+
| ExpenseManager               |
|------------------------------|
| users: Map<id, User>         |
| balances: Map<User, Map<User, Double>> |
| addExpense(...)              |
| showBalances()               |
| showBalance(userId)          |
+------------------------------+
```

---

## 🧪 Supported Split Strategies

1. **EqualSplit** – Splits the amount equally among participants
2. **ExactSplit** – Splits by exact amount per participant
3. **PercentageSplit** – Splits by percentage contribution

---

## 🧠 Balance Tracking Logic

```java
// If A pays 100 for B and C (equal split)
balances[A][B] += 50
balances[A][C] += 50
balances[B][A] -= 50
balances[C][A] -= 50
```

Balances are stored as:

```java
Map<User, Map<User, Double>>
```

---

## 🧰 Features for Extensibility

* ✅ Add new split strategies easily via `SplitStrategy` interface
* ✅ Easy to plug in group functionality
* ✅ Clear encapsulation of balance and transaction logic

---

## 🚀 Example Use Case

```java
ExpenseManager manager = new ExpenseManager();

User u1 = new User("U1", "Alice", "alice@mail.com", "999999");
User u2 = new User("U2", "Bob", "bob@mail.com", "888888");

manager.addUser(u1);
manager.addUser(u2);

// Create equal split
manager.addExpense(ExpenseType.EQUAL, 1000, u1, List.of(u1, u2), null);

manager.showBalances();
// Output: Bob owes Alice: 500
```

---

## 🧩 Potential Enhancements

* Add transaction history per user
* Add notifications (Observer Pattern)
* Add group settlement logic
* Add persistent storage (DB integration)
* Support currencies, exchange rates

---

## 📁 Suggested Package Structure

```
splitwise/
├── model/
│   ├── User.java
│   ├── Expense.java
│   ├── Split.java
│   ├── Group.java
├── strategy/
│   ├── SplitStrategy.java
│   ├── EqualSplitStrategy.java
│   ├── ExactSplitStrategy.java
│   ├── PercentageSplitStrategy.java
├── factory/
│   ├── SplitStrategyFactory.java
├── service/
│   ├── ExpenseManager.java
├── enums/
│   ├── ExpenseType.java
├── Main.java
```

---

Let me know if you'd like me to:

* Generate full code implementation
* Add JUnit test cases
* Add database layer (H2/MySQL)
* Generate PlantUML for class diagrams

Just say the word!
