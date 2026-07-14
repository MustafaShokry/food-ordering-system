# Food Ordering System

A console-based Food Ordering System built in **Java** to practice **Object-Oriented Programming (OOP)**, **SOLID principles**, **Design Patterns**, and **Git workflows**.

---

# Features

A customer can:

- View available menu items
- Build an order with multiple items
- Customize each order item
    - Large Size
    - Extra Sauce
- Add an optional delivery note
- Apply a discount code
- Choose a payment method
- Calculate the total order price
- Print an order receipt
- Receive a notification after placing the order

---

# OOP Concepts Demonstrated

## Classes & Objects

The system is modeled using domain objects such as:

- Customer
- Menu
- MenuItem
- Order
- OrderItem

---

## Encapsulation

Each class manages its own state through private fields and exposes behavior through public methods.

Example:

- `Order` calculates its own total.
- `OrderItem` calculates its own subtotal.

---

## Composition

The project heavily relies on composition.

```
Order
 ├── Customer
 └── List<OrderItem>

OrderItem
 └── MenuItem
```

---

## Polymorphism

Implemented using interfaces.

```
PaymentMethod
    ↑
 ├── CashPayment
 ├── CardPayment
 └── InstallmentByValuPayment
```

```
NotificationChannel
       ↑
 ├── EmailNotification
 └── SmsNotification
```

---

# SOLID Principles

## Single Responsibility Principle

Each class has one responsibility.

Examples:

- `OrderService` → Coordinates order operations.
- `ReceiptPrinter` → Prints receipts.
- `ServiceFactory` → Creates application objects.
- `Order` → Represents an immutable order.

---

## Open / Closed Principle

The system is open for extension without modifying existing classes.

Examples:

Adding a new payment method:

```
PaymentMethod

├── CashPayment
├── CardPayment
├── InstallmentByValuPayment
└── ApplePayPayment
```

No changes are required in `OrderService`.

---

## Liskov Substitution Principle

Every implementation of `PaymentMethod` or `NotificationService` can replace another without affecting the system.

---

## Interface Segregation Principle

Small focused interfaces are used.

```
PaymentMethod

NotificationChannel
```

instead of one large interface.

---

## Dependency Inversion Principle

`OrderService` depends on abstractions rather than concrete implementations.

```
OrderService

↓

PaymentMethod

↓

CashPayment / CardPayment
```

---

# Design Patterns

## Builder Pattern

Used to construct immutable objects with optional attributes.

Implemented in:

- Order
- OrderItem

Example:

```java
OrderItem item = OrderItem.builder()
        .menuItem(burger)
        .quantity(2)
        .largeSize(true)
        .extraSauce(true)
        .build();
```

---

## Strategy Pattern

Allows changing the payment algorithm at runtime.

```
PaymentMethod

├── CashPayment
├── CardPayment
└── InstallmentByValuPayment
```

Also used for notifications.

```
NotificationChannel

├── EmailNotification
└── SmsNotification
```

---

## Singleton Pattern

Implemented using the **Bill Pugh Holder Idiom**.

```
ServiceFactory
```

Only one instance exists during the application lifecycle.

---

## Factory Pattern

`ServiceFactory` centralizes object creation.

Creates:

- OrderService
- ReceiptPrinter
- PaymentMethod
- NotificationService
- Menu

The rest of the application never directly instantiates these services.

---

## Dependency Injection

Dependencies are injected through constructors.

Example:

```java
public OrderService(ReceiptPrinter receiptPrinter)
```

instead of

```java
new ReceiptPrinter();
```

inside the service.

---

# Project Structure

```
src
│
├── app.java
│
├── model
│     Customer.java
│     Menu.java
│     MenuItem.java
│     Order.java
│     OrderItem.java
│
├── payment
│     PaymentMethod.java
│     PaymentType.java
│     CashPayment.java
│     CardPayment.java
│     InstallmentByValuPayment.java
│
├── notification
│     NotificationChannel.java
│     NotificationType.java
│     EmailNotification.java
│     SmsNotification.java
│
├── printer
│     ReceiptPrinter.java
│
└── service
      OrderService.java
      ServiceFactory.java
```

---

# Application Flow

```
Main
    │
    ▼
ServiceFactory
    │
    ▼
OrderService
    │
    ▼
Order Builder
    │
    ▼
Immutable Order
    │
    ▼
Payment Strategy
    │
    ▼
Receipt Printer
    │
    ▼
Notification Strategy
```

---

# Git Workflow

This project follows a simplified **Git Flow** workflow.

## Main Branches

```
main
```

Production-ready code.

```
develop
```

Integration branch.

---

## Feature Branches

Each feature is developed in its own branch.

Examples:

```
feature/domain-models

feature/payment

feature/notification

feature/order-service

feature/receipt-printing

feature/main-flow
```

---

# Future Improvements

- Order Repository
- Menu Repository
- File-based menu loading
- Database integration
- Unit testing (JUnit)
- Logging
- Exception handling
- Discount Strategy
- Loyalty Program
- Delivery Strategy