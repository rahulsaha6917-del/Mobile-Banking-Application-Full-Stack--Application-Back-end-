# 📱 Mobile Banking Management System

Welcome to the **Mobile Banking Management System**, a responsive web application that simulates a mobile banking interface — letting users view balances, transfer money, and manage accounts (demo or project version). It’s built with modern web technologies and designed to be intuitive and visually appealing.
This project demonstrates the implementation of a RESTful banking backend system using Java, Spring Boot, JPA, and MySQL with a layered architecture.


---
# Project Title

Short description of the project.

Example:
Mobile Banking Application built with Spring Boot, MySQL, HTML, CSS, and JavaScript that allows users to perform banking operations like login, deposit, withdraw, and transaction management.

---

# 🚀 Features
## Otp Base Varification

## Bank Mail Sender

## User Registration

## User Login

## Deposit Money

## Withdraw Money

## Transfer Money Between Accounts

## Transaction History

## Clear Transaction History

## Password Reset

## User Logout 

## REST API Integration

## MySQL Database Storage

Responsive User Interface
---

# 🛠️ Tech Stack

### Backend
- Java
- Spring Boot
- Spring Data JPA

### Frontend
- HTML
- CSS
- JavaScript

### Database
- MySQL

## User Table

- <img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/5e080c69-2c80-4f3e-a192-53cb551b8338" />

## Otp Table

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/a8d28327-be88-41f2-aa9a-9e06c68bdeb9" />


## Account Balance Table

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/4b534b48-8dd6-4e08-8cf3-3d78db78ff25" />

## Bank Transaction Table

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/da4dbc0f-da0e-4bbe-8f72-993d045c9a8f" />

## Account Money Transfer Table

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/39ede89c-7366-4c5b-95c5-cdbfcaa38dd7" />



### Tools
- Git
- GitHub
- Postman
- VS Code / IntelliJ

---

# 📂 Project Structure

Mobile-Banking-Application

│    
├── src/main/java/com/example/demo



   ├── Controller

   │      └── BankController.java




   ├── Service

   │      └── BankService.java



   ├── Repository

   │      ├── UserRepository.java

   │      ├── AccountRepository.java

   │      └── TransactionRepository.java




   ├── Entity

   │      ├── User.java

   │      ├── Account.java

   │      └── Transaction.java



   │
   └── DemoApplication.java





│
└── application.properties

---

## 2 Configure Database

Create MySQL database

CREATE DATABASE online_banking;

Update application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/online_banking
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update

---

## 3 Run Application

mvn spring-boot:run

or run

OnlineBankingBackendApplication.java

---

# 🔗 API Endpoints

# Bank Mail Sender 

## http://localhost:9093/api/bank/sendMail

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/5e443580-1abb-4897-b3f0-77c97b50e234" />
-----------

## Mail Receive Message

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/a9a594a6-c23c-44f2-aa5a-3eb1bd81e5f5" />

## Otp

## http://localhost:9093/api/bank/sendOtp?phone=9831495378

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/82dfd08a-a67c-4e4b-a306-bf8c95e38a32" />

## Register

## POST /api/bank/register

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/4ff7e34c-669f-4d40-9c6a-ed056437422a" />

## Login
## POST /api/bank/login

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/4bbddbb8-f234-44cc-b039-12ddac3f3c7e" />

## Deposit

## POST /api/bank/deposit

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/f80f5e2a-9e00-4daa-9f67-626a5cef817f" />

## Withdraw

## POST /api/bank/withdraw

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/a7399e26-8582-4f1f-a399-cbd18cfe6ca1" />

## Transfer Money

## POST /api/bank/transfer

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/47b4e86b-364e-4643-88e8-908e3ab844ad" />

## Check Balance

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/21cf47b6-cb9f-4287-8b44-f26d96ad401b" />

## Transaction History

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/157ec353-047f-418e-a720-4a59d06b493b" />

## Clear Transaction History

## DELETE http://localhost:9093/api/bank/transactions/{userId}/clear

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/39293cf9-5afd-4de8-9fbf-15d62bc21c6b" />

## User Logout

## http://localhost:9093/api/bank/logout?userId=1

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/7092b533-fd1d-45e4-8b19-c3d3e0f79955" />

## Reset Password

## POST /api/bank/resetPassword

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/58b8d65d-5011-43fe-9c7e-7b2ee10b44e0" />

---

# 📊 Application Workflow

Register User  
↓  
Login User  
↓  
Deposit / Withdraw  
↓  
Update Balance  
↓  
Store Transaction  

---

# 🎯 Learning Objectives

- Learn Spring Boot REST API
- Understand MVC architecture
- Connect Java backend with MySQL
- Build full-stack web applications

---

# 🤝 Contribution

1. Fork the repository
2. Create new branch
3. Commit changes
4. Push to GitHub
5. Create Pull Request

---

# 👨‍💻 Author

Rahul Saha
