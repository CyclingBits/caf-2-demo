# CAF-2 Demo

A Spring Boot project with Kotlin, Maven, HTMX, and H2 database for managing contractors and their financial limits.

## Project Overview

This application provides functionality to:
- View and manage contractors
- Track financial limits in multiple currencies
- Display detailed contractor information

## Technology Stack

- **Backend**: Kotlin 1.9.21 + Spring Boot 3.2.1 (Java 17)
- **Build Tool**: Maven
- **Database**: H2 (in-memory)
- **Frontend**: Thymeleaf + HTMX + Bootstrap 5.3
- **Tools**: Spring Data JPA, Spring Validation, Spring DevTools

## Project Structure

```
caf-2-demo/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── caf2demo/
│   │   │               ├── controller/    # Web controllers
│   │   │               ├── model/         # Entity models
│   │   │               ├── repository/    # Data access
│   │   │               ├── service/       # Business logic
│   │   │               └── Caf2DemoApplication.kt
│   │   └── resources/
│   │       ├── templates/      # Thymeleaf templates
│   │       ├── application.properties
│   │       └── data.sql        # Sample data
│   └── test/
│       └── kotlin/
└── pom.xml
```

## Data Model

- **Contractor**: Business entity with ID, name, and NIP (tax ID)
- **Limit**: Financial limit with type, value, currency, and validity dates
- **Supported currencies**: PLN, EUR

## API Endpoints

- `GET /` - Home page with contractor list
- `GET /contractors` - Returns contractor list fragment for HTMX
- `GET /contractor/{id}` - Contractor details page
- `GET /contractor/{id}/limits` - Returns limits fragment for a specific contractor

## Building and Running

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

Access the application at http://localhost:8080
Access the H2 console at http://localhost:8080/h2-console

## Development Guidelines

See CLAUDE.md for code style guidelines and development practices.