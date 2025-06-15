# 🥤 Vending Machine – Low-Level Design in Java

This project demonstrates a **Low-Level Design (LLD)** implementation of a Vending Machine system in Java using **Object-Oriented Principles**, **SOLID**, and the **State Design Pattern**.

---

## 🚀 Features

- Insert money into the vending machine
- Select and dispense products
- Handle product availability and change
- Modular design with multiple states (Idle, HasMoney, Dispensing, OutOfStock)
- Inventory management with in-memory product storage

---

## 🧩 Tech Stack

- Java 8+
- OOP + Design Patterns (State)
- No external dependencies

---

## 🧠 Design Principles
- **Singleton Pattern**: Ensures only one instance of the vending machine exists.
- **State Design Pattern**: Manages the vending machine's states (`IdleState`, `ReadyState`, `DispensingState`, `ReturningChangeState`).
- **Inventory Management**: Tracks products and their quantities.
- **Immutable Product Objects**: Ensures product attributes like name and price cannot be modified after creation.
- **Change Calculation**: Handles returning change to the user.

---

## 🗂️ Project Structure

```
User                VendingMachine         State             Inventory          Product
 |                        |                   |                   |                 |
 |--- selectProduct() --->|                   |                   |                 |
 |                        |--- selectProduct()->|                |                 |
 |                        |                   |--- checkAvailability() --------->   |
 |                        |                   |                   |                 |
 |--- insertCoin() ------>|                   |                   |                 |
 |                        |--- insertCoin() --->|                |                 |
 |                        |                   |                   |                 |
 |--- dispenseProduct()-->|                   |                   |                 |
 |                        |--- dispenseProduct() ->|             |                 |
 |                        |                   |--- updateQuantity() ------------->  |
 |                        |                   |                   |                 |
 |--- returnChange() ---->|                   |                   |                 |
 |                        |--- returnChange() --->|             |                 |
 |                        |                   |--- calculateChange() --------->    |
                    # Demo entry point
```
