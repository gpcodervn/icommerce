## Guides

This project is using external config database, so please setup your database and update the git repo.

### How to set up database 

Mysql Database:
    
    * Database URL: 192.168.64.2
    * Create database on mysql with name "icommerce"
    * Username: root
    * Password: 1234567
    * Port: 3306
    * Import database: /external-resources/database/icommerce.sql

If your configuration doesn't match, please update it correctly for all `.properties` file in repo:
https://github.com/gpcodervn/icommerce-config/tree/master/configuration

Currently, we only use one database for all services, but you can create one database for one service.

### How to start

Run the following command:

> mvn clean install

Start multiple applications by profiles:

> java -jar target/ic-config-server-0.0.1-SNAPSHOT.jar

Don't like to start with built jar file, just use this command:

> mvn spring-boot:run

Check server already run: 

> http://localhost:8888/ic-order/dev

Login with:

    username: root
    password: ic-12345678