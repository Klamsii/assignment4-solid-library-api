# Library Management System (Java, JDBC)

## Project Overview

This project is a console-based Library Management System written in Java using JDBC and PostgreSQL.
The application follows a layered architecture and demonstrates backend development concepts such as
CRUD operations, authentication, authorization, and clean code practices.

The project was developed for educational purposes.

## Architecture

The application uses a layered architecture:

Main  
Controller  
Service  
Repository  
PostgreSQL Database  

### Layer responsibilities

Controller  
Handles user interaction and delegates requests to services.

Service  
Contains business logic, performs validation, and implements role-based access control.

Repository  
Handles database operations using JDBC and executes SQL queries.

DTO  
Transfers data between layers and is used for formatted output.

Utils  
Shared utilities such as database connection and reflection.

## Project Structure

src/  
controller/  
service/  
interfaces/  
impl/  
repository/  
interfaces/  
impl/  
model/  
dto/  
exception/  
interfaces/  
utils/  
Main.java  

## Database (PostgreSQL)

### Tables

authors  
id SERIAL PRIMARY KEY  
name VARCHAR(100)

books  
id SERIAL PRIMARY KEY  
name VARCHAR(100)  
format VARCHAR(20)  
author_id INT REFERENCES authors(id)

users  
id SERIAL PRIMARY KEY  
username VARCHAR(50) UNIQUE  
password VARCHAR(255)  
role VARCHAR(20) CHECK (role IN ('ADMIN', 'USER'))

## Authentication and Roles

The application supports user login and registration.

USER role  
View all books  
Show book by ID  
Filter books by format  
Sort books  
View statistics

ADMIN role  
All USER permissions  
Add books  
Remove books  
View all users  
Delete users  
Promote users to ADMIN  
Demote users to USER

Role-based access is enforced in the main menu.

## Features

Books  
Show all books in a formatted table  
Show book by ID  
Add book (ADMIN only)  
Remove book (ADMIN only)  
Filter by EBOOK or PRINTED  
Sort by book name  
Sort by author name

Users (ADMIN)  
View all users  
Delete user  
Promote user to ADMIN  
Demote user to USER

Statistics  
Count how many books each author has

## DTO Usage

The BookWithAuthor DTO is used to display joined data from books and authors tables.
This avoids polluting domain models and keeps presentation logic clean.

## Reflection (RTTI)

Reflection is used to inspect class structure at runtime.
This demonstrates runtime type information and satisfies the reflection requirement.

## Security Notes

Passwords are stored in plain text for educational purposes only.
In real applications, passwords should be hashed.

## How to Run

Create a PostgreSQL database.  
Execute SQL scripts to create tables.  
Configure database credentials in DatabaseConnection.  
Run Main.java.  
Login or register and use the menu.

## Educational Purpose

This project demonstrates Java backend architecture, JDBC database interaction,
SOLID principles, clean code practices, and role-based access control.

## Conclusion

This is a fully functional console-based backend-style application that goes beyond minimal requirements
and demonstrates real-world Java backend development concepts.
