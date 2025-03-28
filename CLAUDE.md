# CAF-2-Demo Project Guidelines

## Build & Testing Commands
- Build: `mvn clean install`
- Run: `mvn spring-boot:run`
- Test: `mvn test`
- Run single test: `mvn test -Dtest=TestClassName`
- Lint: `mvn ktlint:check`
- Format code: `mvn ktlint:format`

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

Run all tests before committing. Always verify your changes with the lint checker.