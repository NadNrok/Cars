Cars Web Application
This repository encompasses the source code for a streamlined web application named "Cars." This project is crafted using the robust Spring Boot framework, which facilitates the creation of enterprise-grade Java applications with minimal setup and configuration.

Project Overview:
Group ID: com.fm
Artifact ID: Cars
Version: 0.0.1-SNAPSHOT
Java Version: 17
Description: This project serves as a demonstration of Spring Boot's capabilities, focusing on developing a web application for managing cars and their categories.

Dependencies:
The project leverages various dependencies managed by Maven, each serving a distinct purpose in the application's architecture and functionality. Some pivotal dependencies include:

Spring Boot Starter Data JPA: Enables working with JPA (Java Persistence API) databases, facilitating efficient data management.
Spring Boot Starter Web: Provides essential components for building web applications, such as embedded web server and web-related features.
Flyway Core: Facilitates database migrations, ensuring smooth transitions between database versions.
PostgreSQL Driver: Enables connectivity with PostgreSQL database, allowing seamless interaction with the database.
Spring Security OAuth2: Provides robust security features, including OAuth2 support for authentication and authorization.
Auth0: Integrates Auth0 authentication and authorization services into the application, enhancing security and user management capabilities.
Project Structure:
The project adheres to a structured organization, following Spring Boot's recommended package structure. Key components include:

Config: Contains configuration classes responsible for application setup and initialization.
Controller: Houses controllers responsible for handling HTTP requests and managing application flow.
Model: Defines entity classes representing domain objects, such as cars and categories.
Repository: Contains repository interfaces for interacting with the database, enabling CRUD operations.
Service: Implements business logic and application-specific functionality, decoupled from controllers and repositories.
Database Schema:
The application utilizes a PostgreSQL database to persist data related to cars and categories. The database schema is represented in SQL, defining tables and their relationships. Below is a snippet of the database schema:

V1__create_cars_tables.sql:

CREATE TABLE car (
    carId INT PRIMARY KEY,
    make VARCHAR(255),
    year INT,
    model VARCHAR(255)
);

CREATE TABLE category (
    categoryId INT PRIMARY KEY,
    category VARCHAR(255)
);

CREATE TABLE car_category (
    car_id INT,
    category_id INT,
    PRIMARY KEY (car_id, category_id),
    FOREIGN KEY (car_id) REFERENCES car(carId),
    FOREIGN KEY (category_id) REFERENCES category(categoryId)
);
How to Run:
To deploy and run the application locally, follow these steps:

Clone this repository to your local machine.
Ensure you have JDK 17 and Maven installed.
Set up a PostgreSQL database and configure the database connection properties in the application.yml file.
Build the application using Maven: mvn clean install.
Run the application: java -jar target/Cars-0.0.1-SNAPSHOT.jar.
Access the application in your web browser at http://localhost:8080.
Contribution:
Contributions to the project are encouraged and welcomed! If you have suggestions for improvements or would like to contribute to enhancing the functionality of the application, feel free to submit a pull request.

Author:
The "Cars" web application was developed by the talented team at com.fm.

Contact:
For any inquiries or support related to the "Cars" web application, please reach out to us at danil.kornieiev.e@gmail.com.

This README provides a comprehensive overview of the "Cars" web application, including its architecture, functionality, setup instructions, and avenues for contribution. Should you have any further questions or require additional information, do not hesitate to contact us. We look forward to your engagement with our project!
