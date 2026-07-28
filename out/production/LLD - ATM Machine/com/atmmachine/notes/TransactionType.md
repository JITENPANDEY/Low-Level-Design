# TransactionType

## Responsibility

Represents all supported ATM transaction types.

It is only used to identify the user's selected transaction.

It does not contain business logic.

---

# Why Enum?

Transaction types are a fixed set of constants.

Enum provides

- Type Safety
- Readability
- Easy switch/lookup

---

# Why no business logic?

Business logic belongs to Transaction Strategy implementations.

Example

WithdrawalTransaction

DepositTransaction

BalanceInquiryTransaction

TransactionType only identifies the transaction.

---

# SOLID

TransactionType does not violate SRP because it only represents constants.

---

# Interview Questions

## Why Enum instead of Class?

Transaction types are finite and predefined.

---

## Does modifying Enum violate OCP?

Technically yes.

Practically acceptable because the list of transaction types is expected to evolve.

Business logic remains unchanged.

---

## Why not implement execute() inside Enum?

Enum should not own business logic.

Strategy classes execute the transaction.

---

# Common Mistakes

❌ Putting business logic inside Enum.

❌ Using String instead of Enum.

Example

"withdraw"

"deposit"

This is error-prone.

Use Enum for compile-time safety.

---

# Key Learning

Enums represent constants.

Strategies represent behavior.