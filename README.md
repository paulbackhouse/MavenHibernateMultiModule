# Maven Multi Module Project - Using Hibernate ORM

This project is built in Java [Spring Boot Framework](https://spring.io/projects/spring-boot) with [BellSoft JDK](https://bell-sw.com/pages/downloads/#mn), with [Maven](https://maven.apache.org/) using the ORM [Hibernate](https://hibernate.org/) for database management.

Database: Postgres, local docker, see https://levelup.gitconnected.com/creating-and-filling-a-postgres-db-with-docker-compose-e1607f6f882f

Follwing Domain Drive Design, using a project with separate modules, as detailed at https://dzone.com/articles/ddd-spring-boot-multi-module-maven-project


## IDEs

As an editor, **VS Code** was my weapon of choice as I found it far easier to work with, debug and be productive with.

**VS Code** has some excellent extensions to help develop and debug in Java/Spring Boot/Maven

- Extensions https://code.visualstudio.com/docs/java/java-spring-boot
- Basic project setup https://code.visualstudio.com/docs/java/java-spring-boot#_create-the-project

However, I used IntelliJ Community Edition to setup the project with the modules as I found it easier to set the project and modules up in IntelliJ (https://www.codejava.net/ides/intellij/create-multi-module-maven-project-intellij)

In hindsight, I could now set the multi module project up in VS Code, with a bit of manual effort,

## Setup 

I will assume **VS Code** is the editor of choice.

- Download repo
- Open top level project (root) folder in VS Code
- split the terminal view
- In one terminal (first time run)
```docker-compose up``` this will setup docker postgres image for you
  - Postgres GUI  https://www.pgadmin.org/download/ - although there are several others
    - **Server** localhost
    - **Port** 5432 
    - Username and password in docker-compose.yml
- Once the docker image is up and running locally (check docker)
  - In VS Code, press F5 - this will start the project up
  - If using IntelliJ, you might have to run Maven Install the first tiem you run the project https://stackoverflow.com/questions/40359336/will-intellij-idea-mvn-install-automatically-when-make-the-project. This might be applicable to VS Code as well the first time

This should start the project up for you - check the terminal

Of Note: Model Mapper is implemented, see http://modelmapper.org/getting-started/

### API Endpoints:

- GET Users: http://localhost:8080/users?pageindex=0&pagesize=10
- GET User Bookings: http://localhost:8080/users/{userId}/bookings
- POST Create/Update User: http://localhost:8080/users
```js   
{
  "username": "",
  "givenName": "",
  "lastName": ""
}
```

## Project Structure

It is a module project, which follows DDD principles

- **application** the web application
- **webapi** the web api module layer
- **services** the business service module layer, implementations of domain service interfaces
- **repository** the repository module layer, implementations of domain repository interfaces
- **domain** the core domain, which describes domain behaviour (interfaces), entities, dtos, aggregates 

### What in the pom is this? (pom.xml)

This is a project/module config file. It describes the meta data and dependencies of the project and modules and other properties (such as plugins).

See: https://maven.apache.org/guides/introduction/introduction-to-the-pom.html#:~:text=A%20Project%20Object%20Model%20or,Maven%20to%20build%20the%20project.&text=Other%20information%20such%20as%20the,such%20can%20also%20be%20specified.

## Useful Exercises

Here are some ideas on exercises that you could try as part of a learning path:

- Create new Booking Api controller and some simple GET, POST methods
- Add delete logic for Bookings
- Add filter criteria for GET users endpoint, for example, filter by lastname and/or given name 
- Create some unit tests in java
- Create a new entity and associated logic, perhaps payments for each booking
- Async is enabled in the application, try creating async methods and logic
- Spring Boot support microservices development https://spring.io/microservices 
