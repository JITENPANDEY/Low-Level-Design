# ATM

## Responsibility

ATM acts as the **Context** in the State Pattern.

It maintains the current workflow state and delegates every user request to the
current state.

ATM does **not** contain business logic such as cash withdrawal, deposit,
or balance validation.

---

# Responsibilities

- Maintain the current ATM state.
- Maintain the currently inserted card.
- Delegate user requests to the current state.
- Hold the CashWithdrawProcessor.

ATM is responsible for workflow coordination, not transaction execution.

---

# Fields

```java
private ATMState currentState;

private Card currentCard;

private final CashWithdrawProcessor cashWithdrawProcessor;
```

---

# Why currentState?

ATM behavior changes depending on its current state.

Example

```
Idle
↓

Has Card
↓

Select Transaction
```

Instead of using if-else or switch-case, ATM delegates the request to the
current state.

Example

```java
currentState.insertCard(this, card);
```

This is the State Pattern.

---

# Why currentCard?

ATM should know which card is currently inserted.

Later states can access the account using

```java
atm.getCurrentCard().getAccount();
```

ATM does not own the Account.

It only owns the currently inserted Card.

---

# Why CashWithdrawProcessor?

Cash dispensing is a separate responsibility.

ATM should not know

- which denomination to dispense
- in which order to dispense notes

Instead ATM delegates this responsibility to CashWithdrawProcessor.

This follows the Single Responsibility Principle.

---

# Why ATM does NOT contain Account?

ATM is a machine.

Account belongs to the Card holder.

Relationship

```
ATM
 |
Card
 |
Account
```

Keeping Account inside ATM creates unnecessary coupling.

---

# Why ATM does NOT contain withdrawal logic?

Incorrect

```java
ATM.withdraw()
```

```java
validateBalance();

account.debit();

dispenseCash();
```

This makes ATM a God Class.

Instead

```
ATM

↓

Current State

↓

Transaction

↓

CashWithdrawProcessor
```

Responsibilities remain separated.

---

# Why delegation?

ATM simply forwards the request.

Example

```java
public void insertCard(Card card) {
    currentState.insertCard(this, card);
}
```

ATM does not know how the operation is performed.

Each state decides the behavior.

This is polymorphism.

---

# Why ATM is NOT Singleton?

Real world

```
ATM-1

ATM-2

ATM-3

ATM-1000
```

There can be thousands of ATM machines.

Making ATM Singleton would model the domain incorrectly.

Singleton should only be used when exactly one instance must exist.

---

# Design Pattern Used

## State Pattern

ATM delegates every request to the current state.

This removes large if-else blocks.

---

# SOLID Principles

## Single Responsibility Principle

ATM manages workflow.

Business logic belongs to Transaction classes.

Cash dispensing belongs to CashWithdrawProcessor.

---

## Open Closed Principle

New states can be introduced without modifying ATM.

---

## Dependency Inversion Principle

ATM depends on the abstraction

```
ATMState
```

instead of concrete state implementations.

---

# Production Improvements

Instead of directly using CashWithdrawProcessor,

introduce

```
CashInventory
```

Responsibilities

CashInventory

- Maintain note count.

CashWithdrawProcessor

- Decide dispensing algorithm.

This follows SRP more strictly.

---

# Trade-offs

Current design is intentionally simplified for interviews.

Production systems may additionally include

- AuthenticationService
- BankingService
- CashInventory
- Database
- Notification Service
- Audit Service

These are omitted to keep the design focused.

---

# Interview Questions

## Q1. Why State Pattern instead of Enum?

Enum only represents state.

Behavior still requires if-else or switch-case.

State Pattern encapsulates behavior inside individual state classes.

---

## Q2. Why ATM delegates instead of implementing logic?

To separate workflow from business logic.

ATM coordinates.

States and Transactions perform the work.

---

## Q3. Why ATM contains Card but not Account?

ATM interacts with the inserted Card.

Account can always be obtained from the Card.

Keeping Account directly inside ATM creates unnecessary coupling.

---

## Q4. Why ATM is not Singleton?

Because multiple ATM machines exist in the real world.

Singleton would not correctly model the domain.

---

## Q5. Why no withdraw() method in ATM?

Withdrawal is a business transaction.

ATM only coordinates workflow.

Transaction Strategy executes the operation.

---

## Q6. Why keep CashWithdrawProcessor inside ATM?

ATM owns the cash dispensing mechanism.

Dispensing logic should not be implemented inside ATM.

---

## Q7. Which SOLID principles are used?

- SRP
- OCP
- DIP

---

# Key Learnings

- ATM is the Context of the State Pattern.
- ATM coordinates workflow.
- ATM delegates responsibilities.
- Avoid making ATM a God Class.
- Workflow and business logic should remain separate.

---
# Common Mistakes

❌ Storing Account inside ATM

Reason:
ATM is not the owner of Account.

--------------------------------

❌ Writing withdrawal logic inside ATM

Reason:
Violates SRP.

--------------------------------

❌ Using Enum + if-else

Reason:
Violates OCP.

--------------------------------

❌ Making ATM Singleton

Reason:
Real world has multiple ATM machines.
