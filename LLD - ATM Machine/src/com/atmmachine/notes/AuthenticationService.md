# AuthenticationService

## Responsibility

Responsible for validating the user's PIN.

This class is used by HasCardState during authentication.

---

# Responsibilities

- Validate entered PIN.
- Return authentication result.

It does not perform any banking operations.

---

# Methods

```java
boolean authenticate(Card card, String enteredPin)
```

Returns

- true → PIN is valid
- false → PIN is invalid

---

# Why separate service?

Authentication is a separate responsibility.

Keeping it outside the State class follows the Single Responsibility Principle.

---

# Current Implementation

Interview version

```java
return card.getPin().equals(enteredPin);
```

---

# Production Improvements

Instead of storing plain PIN,

store

- PIN Hash

Validate using

```java
passwordEncoder.matches(...)
```

Future responsibilities

- Retry counter
- Card blocking
- Card retention
- Audit logging
- OTP / MFA

---

# SOLID Principles

## Single Responsibility Principle

AuthenticationService performs only authentication.

---

## Open Closed Principle

Authentication mechanism can change without modifying HasCardState.

---

# Common Mistakes

❌ Validating PIN directly inside State.

❌ Combining authentication with banking operations.

❌ Storing plain PIN in production.

---

# Interview Questions

## Why AuthenticationService?

Authentication is an independent business concern.

---

## Why not validate PIN inside Card?

Card is a domain model.

Authentication is a business service.

---

## What changes in production?

- Hashed PIN
- Retry limit
- Card blocking
- Audit logs
- MFA support

---

# Key Learning

Keep authentication separate from workflow and banking operations.