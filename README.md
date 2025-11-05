# Week Two Homework - Department-Employee CRUD application

This is a minimal Java application demonstrating CRUD (Create, Read, Update, Delete) operations.

## Tech Stack

* **Java**
* **Spring Boot**: Used for the application framework.
* **Spring Data JPA**: Used for data persistence.
* **H2 Database**: An in-memory, embedded database.

## Building the Project

This is a standard Maven project. To build the project, run:

```bash
./mvnw clean package
````
## Folder Structure
````
advice:    	Contains global exception handling logic, using @RestControllerAdvice to manage error responses across the application.
annotation:	Holds custom annotations defined for use within the project, to reduce boilerplate or enforce business logic.
config:    	Contains configuration classes necessary to initialize and customize the Spring application.
controller:	Houses the REST API endpoints (@RestController), handling incoming HTTP requests and returning responses.
dto:        Data Transfer Objects, which are classes used to shape and transfer data between the client and the application layers.
entity:    	Defines the JPA entities (@Entity), which represent the data models and map directly to the database tables (e.g., Department).
exception: 	Contains custom exception classes specific to the application's business logic, used for structured error reporting.
repository:	Holds the Data Access Objects (DAOs) (@Repository), which interact directly with the H2 database to perform CRUD operations.
service:  	Contains the business logic layer (@Service), coordinating between the controller and the repository.
'ScrantonApplication.java'	The main entry point class with the @SpringBootApplication annotation to launch the application.
````
## Running the Application

Once built, you can run the application:

```bash
java -jar target/*.jar
```

The application will start, and the H2 database will be initialized in memory.
