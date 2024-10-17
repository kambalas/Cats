









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
### Accessing the Application
### The application will start on port 8082.
```
API Endpoints:
Get All Cats: GET http://localhost:8082/cats
Get Cat by ID: GET http://localhost:8082/cats/{id}
Create Cat: POST http://localhost:8082/cats
Update Cat: PUT http://localhost:8082/cats/{id}
Delete Cat: DELETE http://localhost:8082/cats/{id}
```
### Swagger UI
### Access the API Documentation
### You can interact with the API using Swagger UI:
```

URL: http://localhost:8082/swagger-ui.html
```
### H2 Console
```
Access the H2 In-Memory Database Console
URL: http://localhost:8082/h2-console
JDBC URL: jdbc:h2:mem:catdb
Username: sa
Password: (leave empty)
Validations
```
### Example Request and Validation post /cats http://localhost:8082/swagger-ui/index.html#/cat-controller/createCat
```
{
  "id": 0,
  "name": "",
  "breed": "",
  "age": -1,
  "color": "",
  "dateOfBirth": "2025-10-17T15:04:51.687Z"
}
```
### Error Response:
```
{
  "color": "Color is mandatory",
  "name": "Name is mandatory",
  "dateOfBirth": "Date of birth must be in the past",
  "breed": "Breed is mandatory",
  "age": "Age must be zero or positive"
}
```
### Pagination and Sorting
```
You can use pagination and sorting with the Swagger UI:

URL: http://localhost:8082/swagger-ui/index.html#/cat-controller/getCats
```
### Spring Security
```
Credentials
The application uses in-memory authentication. The default credentials are:

Username: user
Password: password
Example Request with Authentication:
curl -u user:password -X POST http://localhost:8080/cats \
     -H "Content-Type: application/json" \
     -d '{"name":"Kate","breed":"Laukine","age":3,"color":"Ruda"}'
```
### Docker
```
Build the Docker Image
docker build -t cat-app .
Run the Docker Container
docker run -p 8082:8082 cat-app
Access After Running Docker:
Swagger UI: http://localhost:8082/swagger-ui.html
API Endpoints: http://localhost:8082/cats
H2 Console: http://localhost:8082/h2-console
