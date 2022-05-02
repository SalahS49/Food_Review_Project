# Food_Review_Project


Table of Contents:

Getting Started

Scope

Installing

Authors

Acknowledgments








### Getting Started
Database schemas are kept in src/main/resources/schema-mysql.sql, this will need to be run before the application can be executed.

### Prerequisites

What things you need to install the software and how to install them

```
GIT
GitHub
Jira
MySQL
Java
Maven
JUnit
Mockito
```


### Scope
Code fully integrated into a Version Control System using the feature-branch model: main/dev/multiple features
A project managment board (Jira) with full expansion on user stories, acceptance criteria and tasks needed to complete the project
A risk assessment which outlines the issues and risks faced during the project timeframe
A relational database, locally or within the Cloud, which is used to persist data for the project
A functional application ‘back-end’, written in a suitable framework of the language covered in training (Java/Spring Boot), which meets the requirements set on your Scrum Kanban board
A build (.jar) of your application, including any dependencies it might need, produced using an integrated build tool (Maven)
A series of API calls designed with postman, used for CRUD functionality. (Create, Read, Update, Delete)
Fully designed test suites for the application you are creating, including both unit and integration tests

### Installing
Import SQL schema:
mysql -u user -p < src/main/resources/schema-mysql.sql
Build the application in your IDE with maven (optional). Note: a pre-built jar may already exist in the target/ directory, check this if you don't want to overwrite it.
mvn clean install
Run the application in your terminal
java -jar target/bed-0.0.1-SNAPSHOT.jar
Built With
Maven - Dependency Management

A compiled version of the application exists within the base proejct directory. This can be run in your terminal with:

java -jar bed-0.0.1-SNAPSHOT.jar
Prerequisites
If you do not have an installation of MySQL, a comprehensive installation guide for your device can be found here.

MySQL version 14.14; any version between this and the latest will work equally well.

Java JDK version 16.0.1. You can find a comprehensive installation guide for this here

Project Lombok (latest); this is used to generate bioler-plate code through class annotations. An installation guide is available here.

Maven (latest); Maven is our build management tool, used to manage dependencies and build our application. Find an installation guide here.

Springboot (latest); Springboot is what we use to create a RESTful API service, it can be added as a Maven dependency.


### Authors
Salah Salah - 
Backend+Frontend repos-SalahS49

License
For help in Choosing a license

### Acknowledgments
Morgan Walsh 
