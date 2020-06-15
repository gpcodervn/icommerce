### Guides

Please make sure, you already started ic-discovery server.

Run the following command:

> mvn clean install

> java -jar -Dspring.profiles.active=dev /target/ic-audit-0.0.1-SNAPSHOT.jar

Start multiple applications by profiles:

> java -jar -Dspring.profiles.active=dev,ic-audit-replica01 /target/ic-audit-0.0.1-SNAPSHOT.jar

> java -jar -Dspring.profiles.active=dev,ic-audit-replica02 /target/ic-audit-0.0.1-SNAPSHOT.jar

## Don't like to start with built jar file, just use this command:

> mvn spring-boot:run -Dspring-boot.run.profiles=dev

> mvn spring-boot:run -Dspring-boot.run.profiles=dev,ic-audit-replica01

> mvn spring-boot:run -Dspring-boot.run.profiles=dev,ic-audit-replica02

Check API:

> POST http://localhost:8060/api/v1/customer-audits

with JSON body:

    {
    	"feature": "product",
    	"action": "search",
    	"content": "{\"keyword\":\"mac\"}"
    } 

Authorization:

    Bearer token: please use API /v1/auth/sign-in
    
You will receive a response:

    {
        "id": 16,
        "action": "search",
        "feature": "product",
        "content": "{\"keyword\":\"mac\"}",
        "createdBy": 1,
        "createdAt": "2020-06-15T00:29:36.049863"
    }
