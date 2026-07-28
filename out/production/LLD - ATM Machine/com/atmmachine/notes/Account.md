# ATM Low Level Design (LLD)

## Class 1 : Account

### Responsibility

Represents a bank account.

It owns the account balance and exposes only behaviors that directly modify
its own state.

---

### Fields

```java
private String accountNumber;
private double balance;
```

---

### Methods

```java
credit(double amount);

debit(double amount);

getBalance();
```

---

### Why is debit() inside Account?

Because Account owns the balance.

Changing the balance is the responsibility of Account.

However, business workflows like

- Balance Validation
- Daily Withdrawal Limit
- Fraud Detection
- Audit Logging
- Notifications

should NOT be inside Account.

These responsibilities belong to BankingService.

---

### Design Principle

Entity owns state.

Entity can also contain behaviors that directly modify its own state.

Avoid making Entity a God Class.

Avoid making Entity completely anemic.

---

### Production Consideration

Thread safety should be handled by

- BankingService
- Database Transactions
- Optimistic/Pessimistic Locking

instead of synchronizing the entity.

---