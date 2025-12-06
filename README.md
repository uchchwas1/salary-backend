# Salary Management System - Backend API

This is the **Spring Boot Server** for the Employee Payroll Management System. It provides a secure REST API to manage employees, process monthly salary payments, and handle financial transactions for the company.

Built as part of a technical assignment to demonstrate **Spring Boot 3**, **JWT Security**, and **Complex Business Logic** implementation.

## 🚀 Features

* **Authentication & Security:**
    * Secure Login API using **JWT (JSON Web Token)**.
    * Stateless session management (Spring Security 6).
    * Role-based access control (Admin).
* **Employee Management (CRUD):**
    * Create, Read, and Delete employees.
    * Enforces 4-digit unique Employee IDs.
    * Maintains One-to-One relationship with Bank Accounts.
* **Payroll Engine:**
    * **Dynamic Calculation:** Calculates salary based on Grade (1-6) using the formula: `Basic + (Increment * 5000) + 20% Rent + 15% Medical`.
    * **Transactional:** Ensures atomic updates (Company Debit + Employee Credit) to prevent data inconsistency.
* **Financial Handling:**
    * Tracks Company Account Balance.
    * Prevents salary processing if funds are insufficient (throws custom 400 Bad Request).
    * API to add funds to the company account.

## 🛠️ Tech Stack

* **Language:** Java 17
* **Framework:** Spring Boot 3.4.0
* **Database:** MySQL
* **ORM:** Spring Data JPA (Hibernate)
* **Security:** Spring Security 6 + JJWT (0.12.6)
* **Build Tool:** Maven

## 📋 API Endpoints

### 🔐 Authentication
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/auth/login` | Login to get JWT Token |

### 👥 Employees
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/employees` | Get all employees (Optional: `?limit=4`) |
| `GET` | `/api/employees/{id}` | Get employee by ID |
| `POST` | `/api/employees` | Create a new employee |
| `DELETE` | `/api/employees/{id}` | Delete an employee |

### 💸 Payroll & Finance
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/payroll/pay-salary` | Process salary. Query param: `?lowestGradeBasic=15000` |
| `GET` | `/api/payroll/company/balance` | View current company funds |
| `POST` | `/api/payroll/company/add-money` | Add funds to company account |

## ⚙️ Setup & Installation

### 1. Prerequisites
* Java Development Kit (JDK) 17 or higher
* MySQL Server installed and running
* Maven

### 2. Database Configuration
1.  Open your MySQL Workbench/Terminal.
2.  Create a database named `salary_db`:
    ```sql
    CREATE DATABASE salary_db;
    ```
3.  Open `src/main/resources/application.properties` and update your MySQL username and password:
    ```properties
    spring.datasource.username=root
    spring.datasource.password=YOUR_PASSWORD
    ```

### 3. Run the Application
1.  Clone the repository:
    ```bash
    git clone [https://github.com/uchchwas1/salary-backend.git](https://github.com/uchchwas1/salary-backend.git)
    cd salary-backend
    ```

2.   Clone the frontend repository:
   ```bash
    git clone [https://github.com/uchchwas1/salary-frontend.git](https://github.com/uchchwas1/salary-frontend.git)
    cd salary-frontend
```    

3. Build and Run using Maven Wrapper:
    ```bash
    ./mvnw spring-boot:run
    ```
    *(Or run the `SalaryBackendApplication.java` file from IntelliJ IDEA / Eclipse)*

### 4. Default Data (Auto-Loaded)
When the application starts, it automatically runs `data.sql` to populate the database with:
* **1 Admin User**
* **1 Company Account** (Balance: 500,000)
* **10 Employees** (Grades 1-6)

## 🔑 Default Credentials

Use these credentials to log in via the Frontend or Postman:

* **Username:** `admin`
* **Password:** `password`

## 📂 Project Structure

```text
src/main/java/com/uchchwas/salarysystem
├── config/             # Security & CORS Configuration
├── controller/         # REST API Controllers (Endpoints)
├── dto/                # Data Transfer Objects (Req/Res bodies)
├── entity/             # JPA Entities (DB Tables)
├── exception/          # Global Exception Handler
├── repository/         # Database Interfaces
├── security/           # JWT Generation & Filter Logic
└── service/            # Core Business Logic (Payroll calc)



