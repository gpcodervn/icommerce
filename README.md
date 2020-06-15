# The iCommerce application

This is a very simple online shopping application using Java 11, Spring Boot, Spring Cloud + JPA/Hibernate + JUnit.

A very limited set of functionalities:

1. The application is simply a simple web page that shows all products on which customers can filter, short and search for products based on different criteria such as name, price, brand, colour etc.
2. All product prices are subject to change at any time and the company wants to keep track of it.
3. If a customer finds a product that they like, they can add it to their shopping cart and proceed to place an order.
4. For audit support, all customers' activities such as searching, filtering and viewing product's details need to be stored in the database.
However, failure to store customer activity is completely transparent to customer and should have no impact to the activity itself.
5. Customer can login simply by clicking the button “Login with Facebook”. No further account registration is required.
6. No online payment is supported yet. Customer is required to pay by cash when the product got delivered.

## What did I apply?

### Some software development principles applied

1. SOLID Principles
2. DRY Principle
3. KISS Principle
4. Modularity: split each business domain in each module
5. Abstraction: define abstraction layer for service
6. Consistency: the same structure for each module, same way for receiving request, returning response, ...

### Some design patterns

1. Builder Pattern
2. Factory Method Pattern
3. Chain of responsibility Pattern

### Others

Besides that I also AOP for listening on search/ view event on service and audit the customer's activity. 
We can enable/ disable this feature easily without any affect to the business logic.

## Tools used
    - JDK 11
    - Intellij
    - Maven
    - Spring boot 2.3.1.RELEASE
    - Spring Cloud Hoxton.SR5
    - Mysql
    - Junit 5
    - Lombok

### Libraries

    - spring-cloud-config-server : Config server which provides an HTTP resource-based API for external configuration, in this project we stored the database configuration on Github.
    - spring-cloud-starter-config : provides server-side and client-side support for externalized configuration in a distributed system.
    - spring-cloud-starter-netflix-eureka-server : Eureka Server (Service Registry). This library allows services to find and communicate with each other without hard-coding hostname and port.
    - spring-cloud-starter-netflix-eureka-client : Eureka Client, for registering the service with Service Registry.
    - spring-cloud-starter-netflix-ribbon : Load balancer client.
    - spring-boot-starter-actuator : monitor and manage the application.
    - spring-boot-starter-web : for building web, including RESTful, applications.
    - spring-boot-starter-test : for testing the web RESTful API.
    - spring-boot-starter-aop : for aspect-oriented programming with Spring AOP and AspectJ. We use this feature for implementing the customer audit feature.
    - spring-boot-starter-data-jpa: for using Spring Data JPA with Hibernate.
    - mysql-connector-java:  JDBC driver for MySQL.
    - spring-boot-starter-validation: for using Java Bean Validation with Hibernate Validator.
    - spring-boot-starter-security: for using Spring Security
    - spring-security-test: for the testing Spring Security
    - jjwt: JSON Web Token Support For Java.
    - auth0: Java implementation of JSON Web Token (JWT).
    - spring-boot-devtools: Developer Tools for supporting Automatic Restart, LiveReload.
    - log4jdbc-spring-boot-starter: for using Log4jdbc with Spring Boot, it can log SQL that is ready to run with actual values.
    - modelmapper: to make object mapping easy, by automatically determining how one object model maps to another, based on conventions.
    - commons-lang3: The utilities for supporting the operations on String, Number, Reflection, Random, ...
    - commons-collections4: Commons Collections for supporting the operations on Collections data structures in Java.
    

## Folder structure
```
root
|--- ic-backend
|---|--- ic-account : The API for authentication
|---|--- ic-audit : The API for the audit the customer's activities
|---|--- ic-order : The API for the order management
|---|--- ic-product : The API for the product management
|---|--- ic-core : The core feature of iccommerce application
|--- ic-config-server : Provides an HTTP resource-based API for external configuration
|--- ic-discoverry-server : This is a service registry (service discovery server)
|--- external-resources : Including architecture, database design, SQL script, api postman collection
|--- README : Introduce about the project and guideline
```

## Design

### Module dependencies

![](/external-resources/architecture/iCommerce-Dependency.png)

### Spring Cloud dependencies

![](/external-resources/architecture/iCommerce-Spring-Cloud.png)

### Database

Database for authentication (account) service

![](/external-resources/database/diagram/ic-account-db-diagram.png)

Database for product service

![](/external-resources/database/diagram/ic-product-db-diagaram.png)

Database for order service

![](/external-resources/database/diagram/ic-order-db-diagram.png)

Database for audit service

![](/external-resources/database/diagram/ic-audit-db-diagram.png)

## How to run the application

### Start Config server

Please follow [this guideline](/ic-config-server/README.md)

### Start Discovery (Service Registry) server

Please follow [this guideline](/ic-discovery-server/README.md)

### Start API of icommerce services

Please follow [this guideline](/ic-backend/README.md)

## How to verify all services was running

Please go to the discovery server at this link: http://localhost:9000/

![](/external-resources/screenshot/service-registry.png)
