# 🏦 Banking Management System — Hibernate

I built this project while learning Hibernate to go beyond tutorials and actually apply ORM concepts to something that feels like a real application. It's a console-based banking system built with **Java, Hibernate, and MySQL** — nothing fancy, but it covers enough ground to make Hibernate concepts really click.

---

## 🚀 What It Can Do

- 🏦 Open a new bank account
- 📋 List all existing accounts
- 🔍 Search for an account and check its balance
- ➕ Deposit money (with basic authentication)
- ➖ Withdraw money (with balance checks)
- 🗑️ Delete an account
- 🔄 Handle all of the above with proper Hibernate transactions

---

## 🛠️ Built With

- **Java** — core logic
- **Hibernate ORM** — handles all the database talking
- **MySQL** — where the data lives
- **Maven** — dependency management
- **Eclipse IDE** — where it was written

---

## 📚 Hibernate Concepts I Practiced

- Entity Mapping
- `SessionFactory` and `Session` Management
- Transaction Handling
- CRUD Operations via Hibernate
- HQL (Hibernate Query Language)

---

## 🗂️ Project Structure

```
BankProject/
│
├── entity/        # Mapped Java classes
├── dao/           # Database operations
├── service/       # Business logic
├── config/        # Hibernate config
└── Main.java      # Start here
```

---

## 💻 How It Works

When you run it, you get a simple numbered menu in the console:

```
1. Create a bank account
2. View all accounts
3. Search for an account
4. Check account balance
5. Deposit money
6. Withdraw money
7. Delete an account
```

Pick a number, follow the prompts, and Hibernate takes care of all the database work behind the scenes — no raw SQL needed.

---

## ⚙️ Running It Yourself

**You'll need:**
- Java JDK 8+
- MySQL
- Maven
- Any Java IDE (I used Eclipse)

**Steps:**

```bash
# Clone the repo
git clone https://github.com/Harshavardhan-1725/Banking-Management-System-Hibernate.git
```

1. Create a MySQL database
2. Open `hibernate.cfg.xml` and plug in your database credentials
3. Run `mvn clean install`
4. Launch the main class and you're good to go

---

## 💡 What I Actually Learned

This project taught me things tutorials didn't:

- Hibernate makes you think differently about data — you work with objects, not rows
- Setting up `SessionFactory` correctly took more trial and error than I expected
- Transactions aren't optional in a banking context — skipping them breaks everything fast
- The gap between "understanding a concept" and "debugging it at 2am" is real

---

## 📌 What's Next

- [ ] User login and authentication
- [ ] Transaction history
- [ ] Account update functionality
- [ ] Better exception handling
- [ ] Migrate to Spring Boot
- [ ] Add a REST API layer

---

## 👨‍💻 About

Made by **Harshavardhan** as part of learning Java backend development.
If you have feedback or ideas, feel free to reach out — always happy to hear them.

---

⭐ If this helped you in any way, a star would mean a lot!
