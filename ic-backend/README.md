### iCommerce System

## Tools used
    - Spring boot 2.3.1.RELEASE
    - Junit 5
    - Mysql 8.0
    - JDK 11
    - Intellij
    - Lombok
    - Maven
   
## Folder Structure
    controller: Product restful API 
    dao: Product repository which extends JpaRepository to obtain DB operation.
    service: service layer
    exception: custom exception handling of restful API
    entity: Product and ProductAudit entities    
    test: Unit test for searching feature
    
## How to set up
    Mysql Database
    * Database URL: 192.168.64.2
    * Create database on mysql with name "icommerce"
    * Username: root
    * Password: 1234567
    * Port: 3306

    If your configuration doesn't match, Please update these configurations at: application-dev.properties
    
    That's it for setting up

## Building JAR File
    
Run mvn command:
> mvn clean install
    