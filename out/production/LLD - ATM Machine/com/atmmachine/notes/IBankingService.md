# BankingService

## Responsibility

Performs all banking operations on an account.

---

# Responsibilities

- Withdraw money
- Deposit money
- Fetch account balance

---

# Why BankingService?

Separates banking rules from transaction workflow.

Transactions delegate banking operations to this service.

---

# Current Implementation

Interview version delegates directly to Account.

Future versions may include:

- Daily withdrawal limit
- Minimum balance validation
- Fraud detection
- Core Banking System integration
- Audit logging
- Database updates

---

# SOLID Principles

## SRP

Handles only banking operations.

---

## OCP

Business rules can evolve without changing Transaction classes.

---

# Common Mistakes

❌ Calling Account methods from every transaction.

❌ Mixing authentication with banking operations.

❌ Putting banking logic inside ATM.

---

# Interview Questions

Q. Why BankingService if it only delegates?

Answer:
It centralizes banking logic and provides a single place for future business rules.

---

# Key Learning

ATM manages workflow.

Transaction coordinates execution.

BankingService performs banking operations.