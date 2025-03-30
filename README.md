# CAF-2 Demo

A Spring Boot project with Kotlin, Maven, HTMX, and H2 database for managing contractors, financial limits, CAFs (Contract Amendment Forms), IZPs, and ratings.

## Project Overview

This application provides functionality to:
- View and manage contractors
- Track financial limits in multiple currencies
- Manage CAFs (for limit increases, extensions, and suspensions)
- Track IZPs (Investment plans)
- Manage contractor ratings
- Track requirements for different CAF types

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
│   │   │               │   ├── CafController.kt
│   │   │               │   ├── ContractorController.kt
│   │   │               │   ├── IzpController.kt
│   │   │               │   └── RatingController.kt
│   │   │               ├── model/         # Entity models
│   │   │               │   ├── Caf.kt
│   │   │               │   ├── CafRequirement.kt
│   │   │               │   ├── CafStatus.kt
│   │   │               │   ├── CafType.kt
│   │   │               │   ├── CafTypeRequirement.kt
│   │   │               │   ├── Company.kt
│   │   │               │   ├── Contractor.kt
│   │   │               │   ├── Currency.kt
│   │   │               │   ├── Izp.kt
│   │   │               │   ├── Limit.kt
│   │   │               │   ├── LimitStatus.kt
│   │   │               │   ├── LimitType.kt
│   │   │               │   ├── Rating.kt
│   │   │               │   └── Requirement.kt
│   │   │               ├── repository/    # Data access
│   │   │               │   ├── CafRepository.kt
│   │   │               │   ├── CafRequirementRepository.kt
│   │   │               │   ├── ContractorRepository.kt
│   │   │               │   ├── IzpRepository.kt
│   │   │               │   ├── LimitRepository.kt
│   │   │               │   └── RatingRepository.kt
│   │   │               ├── service/       # Business logic
│   │   │               │   ├── CafService.kt
│   │   │               │   ├── ContractorService.kt
│   │   │               │   ├── IzpService.kt
│   │   │               │   ├── LimitService.kt
│   │   │               │   ├── RatingService.kt
│   │   │               │   ├── RequirementService.kt
│   │   │               │   └── validator/
│   │   │               │       ├── AnalysisValidator.kt
│   │   │               │       ├── ApprovalValidator.kt
│   │   │               │       ├── CauseValidator.kt
│   │   │               │       ├── IzpValidator.kt
│   │   │               │       ├── RatingValidator.kt
│   │   │               │       └── RequirementValidator.kt
│   │   │               └── Caf2DemoApplication.kt
│   │   └── resources/
│   │       ├── templates/      # Thymeleaf templates
│   │       │   ├── caf-edit.html
│   │       │   ├── contractor-details.html
│   │       │   ├── contractor-table.html
│   │       │   ├── contractors.html
│   │       │   ├── index.html
│   │       │   ├── izp-table.html
│   │       │   ├── layout.html
│   │       │   ├── limit-table.html
│   │       │   └── rating-table.html
│   │       ├── static/js/
│   │       ├── application.properties
│   │       └── data.sql        # Sample data
│   └── test/
│       └── kotlin/
│           └── com/
│               └── example/
│                   └── caf2demo/
└── pom.xml
```

## Data Model

- **Contractor**: Business entity with ID, name, and NIP (tax ID)
- **Limit**: Financial limit with type, value, currency, and validity dates
- **CAF**: Contract Amendment Form for limit increases, extensions, or suspensions
- **IZP**: Investment plans related to contractors
- **Rating**: Creditworthiness ratings for contractors
- **Requirement**: Requirements that must be met for different CAF types
- **Supported currencies**: PLN, EUR

## API Endpoints

### Contractor Endpoints
- `GET /` - Home page with contractor list
- `GET /contractors` - Returns contractor list fragment for HTMX
- `GET /contractor/{id}` - Contractor details page
- `GET /contractor/{id}/limits` - Returns limits fragment for a specific contractor

### CAF Endpoints
- `POST /limit/{limitId}/caf/increase` - Create increase CAF
- `POST /limit/{limitId}/caf/extension` - Create extension CAF
- `POST /limit/{limitId}/caf/suspension` - Create suspension CAF
- `GET /caf/{id}` - Get CAF details
- `POST /caf/{id}/delete` - Delete CAF

### IZP Endpoints
- `GET /izp` - Get all IZPs
- `GET /contractor/{id}/izp` - Get contractor IZPs

### Rating Endpoints
- `GET /ratings` - Get all ratings
- `GET /contractor/{id}/ratings` - Get contractor ratings

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