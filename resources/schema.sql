CREATE DATABASE library_db;

CREATE TABLE authors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    format VARCHAR(20) NOT NULL
        CHECK (format IN ('EBOOK', 'PRINTED')),
    author_id INT NOT NULL,
    CONSTRAINT fk_author
        FOREIGN KEY (author_id)
        REFERENCES authors(id)
        ON DELETE CASCADE
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
        CHECK (role IN ('ADMIN', 'USER'))
);

INSERT INTO authors (name) VALUES
('Robert Martin'),
('Joshua Bloch'),
('Martin Fowler');

INSERT INTO books (name, format, author_id) VALUES
('Clean Code', 'PRINTED', 1),
('Clean Architecture', 'EBOOK', 1),
('Effective Java', 'PRINTED', 2),
('Refactoring', 'EBOOK', 3);

INSERT INTO users (username, password, role) VALUES
('admin', 'admin123', 'ADMIN'),
('user', 'user123', 'USER');
