# ATMState

## Responsibility

Represents the current workflow state of the ATM.

A state decides

- Which operations are allowed.
- Which operations are invalid.
- Which state should come next.

It does not execute business logic like cash withdrawal or deposit.

---

## Why State Pattern?

ATM behavior changes depending on its current state.

Example:

Idle
↓
Has Card
↓
Select Transaction

Instead of using if-else or switch-case, each state encapsulates its own behavior.

---

## Why Abstract Class instead of Interface?

Almost every state supports only a few operations.

An abstract class provides default implementations for invalid operations.

Concrete states only override the operations they support.

This reduces code duplication.

---

## Why throw IllegalStateException?

Invalid operations represent an invalid workflow.

Examples:

- Withdraw without inserting a card.
- Authenticate PIN before inserting a card.

Failing fast using an exception is better than silently ignoring the request.

---

## Why pass ATM as a parameter?

The state needs access to the ATM context.

Examples:

- Change current state
- Store/remove current card
- Access ATM resources

Without the ATM reference, state transitions are not possible.

---

## Responsibilities

ATMState is responsible for

- Insert Card
- Authenticate PIN
- Select Transaction
- Eject Card

Transaction execution belongs to Strategy classes.

---

## SOLID Principles

### Single Responsibility Principle

Handles only workflow.

Business logic belongs to Transaction classes.

### Open/Closed Principle

New states can be introduced without modifying existing states.

---

## Interview Questions

Q. Why not use Enum?

Because Enum requires if-else/switch-case for behavior.

State Pattern uses polymorphism and avoids conditional logic.

---

Q. Why not Interface?

Because every state would have to implement all methods.

An abstract class provides common default behavior.

---

Q. Why no cashWithdrawal() method?

Cash withdrawal is a business operation.

ATMState should control only the workflow.

Transaction classes execute business logic.

---

## Key Learning

State Pattern controls the workflow.

Strategy Pattern executes business operations.

Keep workflow and business logic separate.