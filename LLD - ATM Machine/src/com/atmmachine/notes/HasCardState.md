# HasCardState

## Responsibility

Represents the state where a card has been inserted and the ATM is waiting for PIN authentication.

---

# Allowed Operations

✅ Authenticate PIN

✅ Eject Card

---

# Not Allowed

❌ Insert another Card

❌ Select Transaction

---

# Workflow

Card Inserted

↓

Authenticate PIN

↓

PIN Valid ?

↓

Yes

↓

SelectTransactionState

---

# Why AuthenticationService?

Authentication is a separate responsibility.

HasCardState coordinates the workflow.

AuthenticationService validates the PIN.

---

# Why transition to SelectTransactionState?

Only authenticated users are allowed to perform transactions.

---

# Production Improvements

- Retry counter
- Card blocking
- Card retention
- Audit logging
- Encrypted PIN validation

---

# SOLID Principles

## SRP

Workflow is handled by HasCardState.

PIN validation is handled by AuthenticationService.

---

## OCP

Authentication implementation can change without modifying HasCardState.

---

# Common Mistakes

❌ Validating PIN directly inside HasCardState.

❌ Performing withdrawal after PIN validation.

❌ Allowing another card insertion.

---

# Interview Questions

Q. Why AuthenticationService?

Q. Why not validate PIN inside State?

Q. What happens after 3 failed attempts?

Q. Why can the user eject the card?

---

# Key Learning

State controls workflow.

Service performs business logic.