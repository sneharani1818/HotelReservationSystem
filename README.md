# 🏨 Hotel Reservation Application (Java)

## 📌 Overview
The **Hotel Reservation Application** is a console-based Java application that allows users to search, book, and manage hotel room reservations. It follows a clean **layered architecture** using models, services, resource (API) classes, and menu-driven UI classes.

The application ensures:
- Rooms cannot be double-booked for the same date range
- Customers are uniquely identified by email
- Administrators can manage rooms and view reservations
- Users receive **recommended alternative dates** when rooms are unavailable

---

## 🧱 Project Structure

src/
│
├── model/
│   ├── RoomType.java
│   ├── IRoom.java
│   ├── Room.java
│   ├── FreeRoom.java
│   ├── Customer.java
│   └── Reservation.java
│
├── service/
│   ├── CustomerService.java
│   └── ReservationService.java
│
├── api/
│   ├── HotelResource.java
│   └── AdminResource.java
│
├── ui/
│   ├── MainMenu.java
│   └── AdminMenu.java
│
└── Main.java


---

## 🧩 Application Layers

### 🔹 Model Layer
Represents the core data objects.

- **RoomType**  
  Enumeration with values: `SINGLE`, `DOUBLE`

- **IRoom**  
  Interface defining room behavior

- **Room**  
  Implements `IRoom`, represents a paid room

- **FreeRoom**  
  Extends `Room`, price is always `0`

- **Customer**  
  Stores customer details with email validation using regex

- **Reservation**  
  Links a customer, room, and check-in/check-out dates

---

### 🔹 Service Layer
Handles business logic and data storage using Java Collections.

#### CustomerService
- Stores customers uniquely by email
- Methods:
  - `addCustomer`
  - `getCustomer`
  - `getAllCustomers`

#### ReservationService
- Stores rooms and reservations
- Prevents overlapping bookings
- Uses loops to:
  - Find available rooms
  - Suggest alternative date ranges
- Methods:
  - `addRoom`
  - `getARoom`
  - `reserveARoom`
  - `findRooms`
  - `findRecommendedRooms`
  - `getCustomersReservation`
  - `printAllReservations`

---

### 🔹 Resource Layer (Facade)
Acts as a bridge between UI and services.

#### HotelResource
Used by the **MainMenu**
- `getCustomer`
- `createACustomer`
- `getRoom`
- `bookARoom`
- `findARoom`
- `getCustomersReservations`

#### AdminResource
Used by the **AdminMenu**
- `getCustomer`
- `getAllCustomers`
- `addRoom`
- `getAllRooms`
- `displayAllReservations`

---

### 🔹 UI Layer (Console Menus)

#### MainMenu Options
1. Find and reserve a room  
2. See my reservations  
3. Create an account  
4. Admin menu  
5. Exit  

If no rooms are available for the selected dates, the system automatically searches for **recommended alternative dates** and displays available rooms.

#### AdminMenu Options
1. See all customers  
2. See all rooms  
3. See all reservations  
4. Add a room  
5. Back to main menu  

---

## 🔁 Room Recommendation Logic
When a room search returns no results:
1. The system shifts the requested dates forward
2. Checks availability again
3. Repeats for a limited number of days
4. Displays recommended rooms if found

This logic is implemented in `ReservationService` and triggered through `HotelResource` and `MainMenu`.

---

## 🚫 Business Rules
- A room cannot be booked more than once for overlapping dates
- Emails must follow the format: `name@domain.extension`
- Room numbers are unique
- Free rooms always have a price of `0`
- All data is stored in-memory using Java Collections

---

## ▶️ How to Run
1. Open the project in a Java IDE
2. Ensure Java 8 or higher is installed
3. Run `Main.java`
4. Follow the on-screen menu prompts

---

## 🛠 Technologies Used
- Java
- Java Collections Framework
- Object-Oriented Programming
- Regular Expressions
- Console-based user interface

---

## 📌 Conclusion
This application demonstrates clean architecture, proper separation of concerns, and strong use of Java OOP principles. It enforces all reservation constraints while providing a user-friendly and extensible design.
