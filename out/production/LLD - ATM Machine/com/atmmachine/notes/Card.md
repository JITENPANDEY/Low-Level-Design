
## Class 2 : Card

### Responsibility

Represents an ATM card.

Card is an identity/authentication object.

It should not contain business logic related to withdrawal or deposit.

---

### Fields

```java
private String cardNumber;

private String pin;

private LocalDate expiryDate;

private Account account;
```

---

### Why is PIN String instead of int?

Reasons:

- PIN is not used for arithmetic operations.
- Leading zeros should be preserved.

Example:

0012

If stored as int

12

Leading zeros are lost.

---

### Production Consideration

Never store

```java
String pin;
```

Instead

```java
String pinHash;
```

Authentication should compare

Entered PIN Hash

with

Stored Hash.

---

### Why no validatePin() method?

Authentication is a workflow.

Future requirements may include

- Failed Attempt Count
- Card Blocking
- OTP
- Biometrics
- Audit Logging

Therefore authentication belongs to

AuthenticationService

instead of Card.

---

### Design Principle

Card stores state.

AuthenticationService owns authentication workflow.

## Responsibility

Represents an ATM Card.

The Card acts as an identity for accessing a bank account.

It is **not responsible** for performing banking operations like withdrawal or deposit.

---

# Why do we need Card?

The ATM machine communicates with the Card first.

The Card is associated with exactly one Account.

```text
ATM
 |
Card
 |
Account
```

The ATM never directly communicates with the Account.

---

# Fields

```java
private final String cardNumber;
private final String pin;
private final LocalDate expiryDate;
private final Account account;
```

---

# Why String instead of int for PIN?

PIN is an identifier, not a numeric value.

Reasons:

- Leading zeros should be preserved.

Example

```
0012
```

If stored as int

```
12
```

which is incorrect.

PIN is never used for arithmetic operations.

Hence String is a better representation.

---

# Why keep Account inside Card?

A Card is linked to a Bank Account.

Once authentication succeeds, the ATM can obtain the Account using

```java
card.getAccount();
```

instead of searching for the Account every time.

For interview purposes this simplifies the design.

In production, Card may only contain card metadata and BankingService may fetch
the Account from the database.

---

# Why no validatePin() method?

Authentication is a business workflow.

Future requirements may include

- Failed login attempts
- Card blocking
- OTP verification
- Biometrics
- Audit Logging
- Fraud Detection

These responsibilities do not belong to Card.

Instead:

```text
AuthenticationService
        |
Authenticate Card
        |
Compare PIN Hash
        |
Return Result
```

This follows the Single Responsibility Principle.

---

# Why no withdraw() method?

Withdrawal is not a responsibility of Card.

Card only identifies the Account.

Withdrawal belongs to

- Transaction
- BankingService

---

# SOLID Principles

### Single Responsibility Principle

Card only stores card-related information.

Authentication and Banking operations are delegated to other classes.

---

# Production Considerations

Never store

```java
private String pin;
```

Instead

```java
private String pinHash;
```

Authentication should compare

```
Hash(Entered PIN)
```

with

```
Stored PIN Hash
```

Never store PIN in plain text.

---

# Interview Questions

### Why is PIN String?

Because PIN is an identifier.

Leading zeros must be preserved.

---

### Why is Account inside Card?

To establish the association between Card and Account.

It simplifies the LLD.

---

### Why no validatePin()?

Authentication is a workflow.

It belongs to AuthenticationService.

---

### Why no withdraw()?

Withdrawal is not the responsibility of Card.

Card is only an identity object.

---

# Key Learning

Entity stores state.

Service coordinates workflows.

Never put unrelated business logic inside an Entity.