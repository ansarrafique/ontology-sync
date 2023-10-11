# Ontology Synchronization Service

The Ontology Synchronization Service is a web-based application that allows users to search for ontologies and synchronize them with a local MongoDB database. The application provides a user-friendly interface for searching (by ontology id) and retrieving ontology details.

## Table of Contents
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [MongoDB Configuration](#mongodb-configuration)
- [Running the Application](#running-the-application)
- [Dockerization](#dockerization)
- [Folder Structure](#folder-structure)
- [Unit and Integration Tests](#unit-and-integration-tests)



## Getting Started

### Prerequisites

Before you begin, ensure you have met the following requirements:

- Node.js is installed on your machine. The application is being tested with the latest version of Node.js (version 20).
- Java (JDK) is installed on your machine. The application is being tested with JDK 17, but it should be compatiable with the other versions of Java.
- Maven is installed on your machine.
- MongoDB is set up or access to a MongoDB database (MongoDB Atlas). Please make sure that you have correctly specified the configuration of the MongoDB database such as *host*, *port*, and the *database name* in the `application.properties` file (cf. [MongoDB Configuration](#mongodb-configuration)).
- Docker is installed if you want to run this application in a Docker container.

### Installation

1. Clone the repository:

   ```sh
   git clone https://github.com/your-username/ontology-sync.git
   ```
2. Navigate to the ontology-sync directory:
	```sh
   cd ontology-sync
   ```
3. Install backend (Spring Boot) dependencies:
	```sh
	# Inside the root directory
	./mvn clean install
    ```
4. Install frontend (React) dependencies:
	```sh
    # Inside the 'frontend' directory
    cd frontend
    npm install
    ```

## MongoDB Configuration

Please configure the `application.properties` file in the resources directory of the project (i.e. back-end module). It contains configurations for the MongoDB database.

```properties
# MongoDB Configuration
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=ontology_db
```

If you prefer to use MongoDB Atlas (a Database-as-a-Service provider) instead of setting up, running, and mantaining an on-premise MongoDB instance, please configure the `application.properties` file as:

```properties
# MongoDB Configuration
spring.data.mongodb.uri = # MongoDB Atlas URI
spring.data.mongodb.database=ontology_db
```

## Running the Application
To run the application locally, follow these two steps:

1. Start the Spring Boot backend:
    ```sh
    # Inside the root directory
    ./mvn spring-boot:run
    ```
2. Start the React frontend:

    ```sh
    # Inside the 'frontend' directory
    cd frontend
    npm start
    ```

The application will be accessible at `http://localhost:3000/`.

## Dockerization

You can containerize the application using Docker. Ensure you have Docker installed.

1. Build the Docker image for the backend (Spring Boot):
	```sh
    # Inside the root directory
	docker build -t ontology-sync-backend .
    ```
2. Run the Docker container for the backend:
	```sh
    docker run -d -p 8080:8080 --name ontology-sync-backend ontology-sync-backend
    ```

The Spring Boot application will be accessible at `http://localhost:8080/ontologies/{ontologyId}`.

3. Build the Docker image for the frontend (React):
	```sh
    # Inside the 'frontend' directory
	docker build -t ontology-sync-frontend .
    ```
4. Run the Docker container for the frontend:
	```sh
    docker run -d -p 3000:3000 --name ontology-sync-frontend ontology-sync-frontend
    ```
    
The front-end will be accessible at `http://localhost:3000`. It provides a user-friendly interface to search an ontology by id. If the ontology is found in the database or in the Ontology Lookup Service (OLS), the details of the ontology will be displayed. Otherwise, **Error: Ontology not found** is displayed.

## Unit and Integration Tests

The project includes unit tests and integration tests for different components of the application:

- **OntologyControllerTest**: Unit tests for the **'OntologyController'** class.
- **OntologyServiceTest**: Unit tests for the **'OntologyService class'**.
- **OntologyLookupServiceTest**: Unit tests for the **'OntologyLookupService class'**.
- **OntologyControllerIntegrationTest**: Integration tests for the **'OntologyController'** class.
