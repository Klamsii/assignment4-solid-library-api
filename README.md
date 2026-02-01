Library Management System (Java, JDBC)
Project Overview

This project is a console-based Library Management System written in Java using JDBC and PostgreSQL.
The application follows a layered architecture and demonstrates core backend concepts such as:

CRUD operations

DTO usage

Authentication and Authorization

Role-Based Access Control (RBAC)

Java Streams and Lambda expressions

Reflection (RTTI)

SOLID principles

Architecture

The project is structured using a clean layered architecture:

Main
 └── Controller
     └── Service
         └── Repository
             └── Database (PostgreSQL)

Layers description

Controller – handles user interaction and delegates logic

Service – contains business logic and validation

Repository – handles database operations using JDBC

DTO – used for data transfer and presentation

Utils – shared utilities (database connection, reflection)

Project Structure
src/
├── controller/
├── service/
│   ├── interfaces/
│   └── impl/
├── repository/
│   ├── interfaces/
│   └── impl/
├── model/
├── dto/
├── exception/
├── interfaces/
├── utils/
└── Main.java

Database (PostgreSQL)
Tables
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

Authentication and Roles

The application supports login and registration.

Roles

USER

View books

Filter and sort books

View statistics

ADMIN

All USER permissions

Add books

Remove books

View all users

Delete users

Promote users to ADMIN

Demote ADMIN to USER

Role-based access is enforced in the Main menu logic.

Features
Books

Show all books in a formatted table

Show book by ID

Add book (ADMIN only)

Remove book (ADMIN only)

Filter by format (EBOOK / PRINTED)

Sort by book name

Sort by author name

Users (ADMIN)

View all users

Delete user

Promote user to ADMIN

Demote user to USER

Statistics

Count how many books each author has

DTO Usage

The BookWithAuthor DTO is used to display joined data from multiple tables
without polluting domain models.

This ensures a clear separation of concerns and clean presentation logic.

Technologies Used

Java

JDBC

PostgreSQL

Java Streams and Lambda expressions

Reflection (RTTI)

Reflection (RTTI)

Reflection is used to inspect class structure at runtime and demonstrate
runtime type information and class metadata inspection.

This satisfies the reflection requirement of the assignment.

Security Notes

Passwords are stored in plain text for educational purposes only.
In a real-world application, password hashing should be applied.

How to Run

Create a PostgreSQL database

Execute SQL scripts to create the tables

Configure database credentials in DatabaseConnection

Run Main.java

Login or register and use the menu

Educational Purpose

This project was created for educational purposes to demonstrate:

Backend architecture

Database interaction using JDBC

Clean code practices

Object-oriented design principles

Conclusion

This project is a fully functional backend-style console application
that goes beyond minimal requirements and demonstrates real-world concepts
used in Java backend development.
