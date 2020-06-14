### Guides

Please make sure, you already started ic-discovery server.

Run the following command:

> mvn clean install

> java -jar -Dspring.profiles.active=dev /target/ic-account-0.0.1-SNAPSHOT.jar

Start multiple applications by profiles:

> java -jar -Dspring.profiles.active=dev,ic-account-replica01 /target/ic-account-0.0.1-SNAPSHOT.jar
> java -jar -Dspring.profiles.active=dev,ic-account-replica02 /target/ic-account-0.0.1-SNAPSHOT.jar

## Don't like to start with built jar file, just use this command:

> mvn spring-boot:run -Dspring-boot.run.profiles=dev
> mvn spring-boot:run -Dspring-boot.run.profiles=dev,ic-account-replica01
> mvn spring-boot:run -Dspring-boot.run.profiles=dev,ic-account-replica02

Check API:

> POST http://localhost:8070/api/v1/auth/sign-in

with JSON body:

    {
    	"userName": "ptgiang56it@gmail.com",
    	"password": "12345678"
    }

You will receive a response:

    {
        "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJpZFwiOjEsXCJ1c2VybmFtZVwiOlwicHRnaWFuZzU2aXRAZ21haWwuY29tXCIsXCJyb2xlQ29kZXNcIjpbXCJBRE1JTlwiXX0iLCJzY29wZXMiOlsiUk9MRV9BRE1JTiJdLCJpc3MiOiJodHRwczovL2ljb21tZXJjZS5jb20vIiwiaWF0IjoxNTkyMTU1NDUzLCJleHAiOjI0NTYxNTU0NTN9.XjaQbidCl6cZeTwKpd0tMsO4R16_r1frGQL4qSarUesGrDAyWLwiQLkp9V7DNIcaFolf16PMolaKoIvetXmz_A",
        "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJpZFwiOjEsXCJ1c2VybmFtZVwiOm51bGwsXCJyb2xlQ29kZXNcIjpudWxsfSIsInNjb3BlcyI6WyJST0xFX1JFRlJFU0hfVE9LRU4iXSwiaXNzIjoiaHR0cHM6Ly9pY29tbWVyY2UuY29tLyIsImlhdCI6MTU5MjE1NTQ1MywiZXhwIjozNzg4MDE1NTQ1M30.qFhlWCY0p8ecfVjMcrSFQUnizePdywZRvSUdFXKLWpqPcEW6NiEqHI5bKw5YI_I7XEwbOL2MpaBSt-wehN3RXw",
        "tokenType": "Bearer"
    }