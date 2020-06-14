### iCommerce System

## Tools used
    - JDK 11
    - Intellij
    - Maven
    - Spring boot 2.3.1.RELEASE
    - Spring Cloud Hoxton.SR5
    - Mysql
    - Junit 5
    - Lombok
   
## Folder Structure

Each service has the same structure:

    controller: The Restful API 
    repository: The Repository which extends JpaRepository to obtain DB operation.
    service: The Business layer
    exception: Custom exception handling of restful API
    entity: The Entity
    configuration: All configuration for service
    model: Define a transfer object for receive request from client and response data to client
    test: Unit test

## Building JAR File

### Build & package services

Run mvn command:

> mvn clean install

### Start account service 

Please follow [this guideline](ic-account/README.md)

### Start audit service 

Please follow [this guideline](ic-audit/README.md)

### Start product service 

Please follow [this guideline](ic-product/README.md)

### Start order service 

Please follow [this guideline](ic-order/README.md)