# Employee Management System

A Spring Boot application for managing employee records.

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- Maven
- H2 Database
- Thymeleaf

## Features

- Add employees
- View employee listing
- View employee details
- Edit employee details

## Employee Fields

- First Name
- Last Name
- Date of Birth
- Department
- Salary
- Manager (optional)

## Prerequisites

- JDK 17

Ensure Java 17 is being used before running the application:

```bash
java -version
```

## Running the Application

Clone the repository:

```bash
git clone https://github.com/yashicaj94/employee-management-system.git
cd employee-management-system
```

Run using Maven Wrapper:

### Windows

```bash
mvnw.cmd spring-boot:run
```

### macOS / Linux

```bash
./mvnw spring-boot:run
```

Once the application starts, open:

```text
http://localhost:8080
```

## Application Navigation

The application provides two main menu options:

- Add Employee
- Employee Listing

Employees listed in the application can also be viewed and edited.
