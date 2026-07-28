# Transaction

## Responsibility

Represents a transaction that can be executed after successful authentication.

Concrete implementations provide the business logic.

Examples

- WithdrawalTransaction
- DepositTransaction
- BalanceInquiryTransaction

---

# Why Strategy Pattern?

Each transaction has a different algorithm.

Instead of using if-else, we use polymorphism.

ATM looks up the required Transaction from a Map and calls execute().

---

# Why Abstract Class?

All transactions require BankingService.

The abstract class shares this common dependency and avoids duplication.

---

# Responsibilities

Transaction

- Defines execution contract.

Concrete Transaction

- Implements business logic.

---

# SOLID

SRP

Each transaction performs only one business operation.

OCP

New transactions can be added without modifying existing ones.

---

# Common Mistakes

❌ Putting all transaction logic inside ATM.

❌ Using if-else for every transaction.

❌ Calling Account methods directly from State.

---

# Interview Questions

Q. Why Strategy Pattern?

Q. Why Abstract Class?

Q. Why not put withdrawal logic in ATM?

Q. Why not use switch-case?

---

# Key Learning

State controls workflow.

Transaction executes business logic.