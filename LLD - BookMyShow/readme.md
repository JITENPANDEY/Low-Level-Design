

# 🎬 BookMyShow Movie Ticket Booking System - LLD (Java)

## 📌 Overview

This project is a **Low-Level Design (LLD)** of a **Movie Ticket Booking System**, inspired by BookMyShow. It simulates the flow of booking movie tickets, managing shows, theaters, and users, with a strong focus on extensible architecture and design patterns.

---

## ✅ Features

* 🎟️ Book movie tickets
* 🪑 Seat selection and availability tracking
* 🏢 Multiple screens and theaters
* ⏰ Show scheduling
* 👤 User management and notifications
* 💸 Pricing strategies (Fixed & Seat-Type based)
* 🧾 Observer Pattern for notifications
* 🏭 Factory Pattern for ticket creation
* ⚡ Movie Caching for fast lookup
* 🔒 Thread safety during booking

---

## 🧱 Project Structure

```bash
com.bookmyshow
├── enums
│   ├── SeatStatus.java           // Status of each seat (AVAILABLE, BOOKED, BLOCKED)
│   ├── SeatType.java             // Types of seat (REGULAR, PREMIUM, RECLINER)
│   └── BookingStatus.java        // Booking state (CONFIRMED, CANCELLED)

├── observer
│   ├── NotificationObserver.java // Observer interface
│   ├── EmailNotificationObserver.java // Email-based implementation
│   └── NotificationService.java  // Service to notify all registered observers

├── model
│   ├── User.java                 // User of the system
│   ├── Movie.java                // Movie details
│   ├── Seat.java                 // Seat configuration
│   ├── Screen.java               // Screen with seats
│   ├── Theatre.java              // Theatre entity
│   ├── Show.java                 // Show with seat availability
│   └── Booking.java              // Booking object

├── strategy
│   ├── PricingStrategy.java      // Strategy interface
│   ├── FixedPricingStrategy.java// Fixed rate strategy
│   └── SeatTypeBasedPricingStrategy.java // Pricing based on seat type

├── factory
│   └── BookingFactory.java       // Factory to create bookings

├── cache
│   └── MovieCache.java           // In-memory cache for movies

├── service
│   └── BookingService.java       // Booking business logic and orchestration

└── BookMyShowDemo.java           // Demo class with main() to simulate the booking flow

```

---

## 🛠️ Design Patterns Used

| Pattern                  | Purpose                                                |
| ------------------------ | ------------------------------------------------------ |
| **Observer**             | Notifies users upon booking confirmation               |
| **Factory**              | Encapsulates the booking object creation               |
| **Strategy**             | Allows flexible pricing logic                          |
| **Singleton (optional)** | For shared services like cache or notification service |
| **Caching**              | For quick access to frequently used movie data         |

---

## 🧾 Technologies

* **Java 17+**
* **OOP Principles**
* **Thread Safety (Synchronized blocks)**
* No external dependencies

---

## 🚀 Sample Flow

1. Create users and movies.
2. Store movies in cache.
3. Setup seats, screen, and theatre.
4. Create show with a movie on a screen.
5. Use `BookingService` with a selected pricing strategy.
6. Attempt seat bookings.
7. Observe booking confirmation and seat availability.

---

## 🧪 Demo Output

```java
Booking confirmed with ID: 3f8e3a67-b18a-4c0e-b9f3-7e245e2c9e1e
Seat already booked: S1
```

---

## 🔄 Extensibility

You can easily extend the system with:

* 🎟️ Refund & cancellation
* 📃 Mini-statements
* 💰 Payment service integration
* 🏙️ City-based filtering for theaters
* 📅 Advanced search by show date/time
* 🌍 Multi-language/multi-region support

---

## 📂 Example Packages

```java
com.bookmyshow.enums              // SeatType, SeatStatus, BookingStatus
com.bookmyshow.model              // User, Movie, Seat, Screen, Theatre, Show, Booking
com.bookmyshow.strategy           // PricingStrategy, FixedPricing, SeatTypeBasedPricing
com.bookmyshow.factory            // BookingFactory
com.bookmyshow.observer           // NotificationObserver, EmailNotificationObserver
com.bookmyshow.cache              // MovieCache
com.bookmyshow.service            // BookingService
```

---

## 👨‍💻 How to Run

* Clone the repo or copy all classes into your IDE.
* Run `BookMyShowDemo.java`.
* Observe seat booking, confirmation, and conflict scenarios.

---

## 🙌 Contributors

Built by \[Your Name] with ❤️ using Java and Clean Code Principles.

---

Let me know if you'd like this in markdown format (`README.md`) or want to include UML diagrams or setup instructions.
