# CAF-2 Demo

A Spring Boot project with Kotlin, Maven, HTMX, and H2 database.

## Project Structure

```
caf-2-demo/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── caf2demo/
│   │   │               ├── controller/
│   │   │               ├── model/
│   │   │               ├── repository/
│   │   │               ├── service/
│   │   │               └── Caf2DemoApplication.kt
│   │   └── resources/
│   │       ├── static/
│   │       │   └── js/
│   │       │       └── htmx.min.js
│   │       ├── templates/
│   │       │   └── layout.html
│   │       └── application.properties
│   └── test/
│       └── kotlin/
│           └── com/
│               └── example/
│                   └── caf2demo/
└── pom.xml
```

## Technology Stack

- **Backend**: Kotlin + Spring Boot
- **Build Tool**: Maven
- **Database**: H2 (in-memory)
- **Frontend**: Thymeleaf + HTMX
- **Tools**: Spring Data JPA, Spring Validation

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