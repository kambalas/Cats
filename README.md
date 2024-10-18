









# Cats Application

This project is a simple Spring Boot web application demonstrating basic CRUD operations, pagination, sorting, validations, and integration with the H2 in-memory database and Swagger UI. It also includes Spring Security for basic authentication and Docker support.

## Project Description

This project showcases various Spring Boot and Java concepts:

- RESTful API for managing `Cat` entities
- Pagination and sorting support
- Validation for entity fields
- H2 in-memory database integration
- Swagger UI for API documentation
- Basic authentication with Spring Security
- Docker support for containerization

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/kambalas/Cats.git
cd Cats
```
### Building the Application using Maven:
```
mvn clean package
```

### Run the application using the following command:
```
java -jar target/cat-0.0.1-SNAPSHOT.jar
```
### The application will start on port 8082.

# Core Requirements
```
1. Entities
Cat Entity Implementation
The Cat entity has been implemented with the following fields:

id (Long, auto-generated)
name (String)
breed (String)
age (Integer)
color (String)
dateOfBirth (Date)

2. Endpoints
API Endpoints Implemented
POST /cats - Create a new cat
GET /cats - Get the list of all cats
GET /cats/{id} - Get details of a specific cat by its ID
PUT /cats/{id} - Update the details of a cat
DELETE /cats/{id} - Delete a cat

3. Database
H2 In-Memory Database Configuration
The application uses an H2 in-memory database for storing cat data.

The H2 console is accessible via the browser at:

URL: http://localhost:8082/h2-console
JDBC URL: jdbc:h2:mem:catdb
Username: sa
Password: (leave empty)

4. Error Handling
Exception Handling Implemented
Common errors such as cat not found, invalid input, and any violations of unique constraints are handled using @ExceptionHandler and @ControllerAdvice.
The application returns meaningful error messages to the client.

5. Testing
Unit Tests for Controller
To run the tests, execute:

mvn test
```
# Optional Items

## 1. Validation
```

Validation constraints have been added to the Cat entity using annotations:

@NotNull and @NotBlank for mandatory fields.
@Size for string length constraints.
@Min(0) for the age field to ensure it's zero or positive.
@Past for the dateOfBirth field to ensure the date is in the past.
The controller uses @Valid to enforce validation rules.

Invalid requests return meaningful error messages.

Example of Invalid Input and Error Messages
Request:

POST /cats
Content-Type: application/json

{
  "id": 0,
  "name": "",
  "breed": "",
  "age": -1,
  "color": "",
  "dateOfBirth": "2025-10-17T15:04:51.687Z"
}
Response:

Status: 400 Bad Request

{
  "color": "Color is mandatory",
  "name": "Name is mandatory",
  "dateOfBirth": "Date of birth must be in the past",
  "breed": "Breed is mandatory",
  "age": "Age must be zero or positive"
}

```
## 2. Pagination and Sorting

```
Pagination and Sorting Implemented
Pagination and sorting have been added to the GET /cats endpoint.
Users can retrieve the list of cats in pages and sort them by name, breed, age, color, or dateOfBirth.
Implemented using Spring Data's Pageable and Sort options.
Using Pagination and Sorting
Endpoint: GET /cats
Parameters:
page: Page number (zero-based index)
size: Page size
sort: Sorting criteria (e.g., name,asc or age,desc)
Example Request:


GET /cats?page=0&size=5&sort=name,asc
Access via Swagger UI:

URL: http://localhost:8082/swagger-ui/index.html#/cat-controller/getCats
```
## 3. Swagger Documentation
```
Swagger/OpenAPI documentation has been added using SpringDoc.
Accessible at:
URL: http://localhost:8082/swagger-ui.html
```
## 4. Security
```

The API endpoints are secured using Spring Security with basic authentication.
Only authenticated users can create, update, or delete cats.
An in-memory user has been configured for authentication.
Authentication Details
Username: user
Password: password
Example Request with Authentication
bash
Copy code
curl -u user:password -X POST http://localhost:8082/cats \
     -H "Content-Type: application/json" \
     -d '{"name":"Kate","breed":"Laukine","age":3,"color":"Ruda"}'
```
## 5. Docker
```
5. Docker

The Spring Boot application has been containerized using Docker.
Building the Docker Image
docker build -t cat-app .

Running the Docker Container
docker run -p 8082:8082 cat-app

Accessing the Application in Docker
Swagger UI: http://localhost:8082/swagger-ui.html
API Endpoints: http://localhost:8082/cats
H2 Console: http://localhost:8082/h2-console
```
