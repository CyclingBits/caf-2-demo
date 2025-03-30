# CAF-2-Demo Project Guidelines

## Build & Testing Commands
- Build: `mvn clean install`
- Run: `mvn spring-boot:run`
- Test: `mvn test`
- Run single test: `mvn test -Dtest=TestClassName`
- Lint: `mvn ktlint:check`
- Format code: `mvn ktlint:format`
- Access H2 console: http://localhost:8080/h2-console

## Code Style Guidelines
- **Formatting**: Follow Kotlin style guide with 4-space indentation
- **Imports**: Organize imports alphabetically, no wildcard imports
- **Naming**: 
  - Classes: PascalCase
  - Functions/Variables: camelCase
  - Constants: UPPER_SNAKE_CASE
- **Types**: Use explicit types for public APIs, inferred types for local variables
- **Error Handling**: Use Result type or exceptions with meaningful messages
- **Documentation**: KDoc comments for all public functions and classes
- **Testing**: Write unit tests for all business logic

## Entity Model

- **Contractor**: Base entity representing a business partner
- **Company**: Company information within a contractor
- **Limit**: Financial limit with type, amount, currency, and validity
  - **LimitType**: Type of financial limit (GUARANTEE, LOAN, etc.)
  - **LimitStatus**: Status of a limit (ACTIVE, EXPIRED, etc.)
- **CAF**: Contract Amendment Form
  - **CafType**: Type of CAF (INCREASE, EXTENSION, SUSPENSION)
  - **CafStatus**: Status of a CAF (DRAFT, ANALYSIS, APPROVAL, etc.)
- **CafRequirement**: Requirements attached to a specific CAF
  - **Requirement**: Base requirement definition
  - **CafTypeRequirement**: Requirements for a specific CAF type
- **Izp**: Investment plans related to contractors
- **Rating**: Credit ratings for contractors
- **Currency**: Supported currencies (PLN, EUR)

## Validation Components

Multiple validators ensure business rules are followed:
- **AnalysisValidator**: Validates CAFs in analysis phase
- **ApprovalValidator**: Validates CAFs in approval phase
- **CauseValidator**: Validates cause/reason documentation
- **IzpValidator**: Validates investment plans
- **RatingValidator**: Validates contractor ratings
- **RequirementValidator**: Validates CAF requirements

## Database Guidelines
- **H2 Database**: In-memory database for development
  - URL: jdbc:h2:mem:caf2db
  - Username: sa
  - Password: password
- **JPA Entities**: Follow standard JPA practices
- **Data Initialization**: Use data.sql for sample data

Run all tests before committing. Always verify your changes with the lint checker.