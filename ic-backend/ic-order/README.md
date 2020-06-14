### Guides

Please make sure, you already started ic-discovery server.

Run the following command:

> mvn clean install

Start multiple applications by profiles:

> java -jar -Dspring.profiles.active=dev,ic-order-replica01 /target/ic-order-0.0.1-SNAPSHOT.jar
> java -jar -Dspring.profiles.active=dev,ic-order-replica02 /target/ic-order-0.0.1-SNAPSHOT.jar

## Don't like to start with built jar file, just use this command:

> mvn spring-boot:run -Dspring-boot.run.profiles=dev,ic-order-replica01
> mvn spring-boot:run -Dspring-boot.run.profiles=dev,ic-order-replica02
