### Guides

Please make sure, you already started ic-discovery server.

Run the following command:

> mvn clean install

> java -jar -Dspring.profiles.active=dev /target/ic-order-0.0.1-SNAPSHOT.jar

Start multiple applications by profiles:

> java -jar -Dspring.profiles.active=dev,ic-order-replica01 /target/ic-order-0.0.1-SNAPSHOT.jar
> java -jar -Dspring.profiles.active=dev,ic-order-replica02 /target/ic-order-0.0.1-SNAPSHOT.jar

## Don't like to start with built jar file, just use this command:

> mvn spring-boot:run -Dspring-boot.run.profiles=dev
> mvn spring-boot:run -Dspring-boot.run.profiles=dev,ic-order-replica01
> mvn spring-boot:run -Dspring-boot.run.profiles=dev,ic-order-replica02

Check API:

> POST http://localhost:8090/api/v1/orders

with JSON body:

    {
    	"orderDetails": [
    		{
    			"productId": 2,
    			"name": "iphone X",
                "price": 1499.0,
                "branchName": "Apple",
                "color": "pink",
                "amount": 2
    		},
    		
    		{
    			"productId": 4,
                "name": "iphone 8 Plus",
                "description": "The best produt in 2020",
                "price": 1499.0,
                "branchName": "Apple",
                "color": "pink",
                "amount": 10
    		}
    	]
    }

Authorization:

    Bearer token: please use API /v1/auth/sign-in
    
You will receive a response:

    {
        "id": 7,
        "no": "yNZgjDYY",
        "price": 17988.0,
        "customerId": 1,
        "orderedAt": "2020-06-14T15:05:09.006312",
        "orderDetails": [
            {
                "id": 2,
                "productId": 2,
                "name": "iphone X",
                "price": 1499.0,
                "amount": 2,
                "branchName": "Apple",
                "color": "pink"
            },
            {
                "id": 4,
                "productId": 4,
                "name": "iphone 8 Plus",
                "price": 1499.0,
                "amount": 10,
                "branchName": "Apple",
                "color": "pink"
            }
        ]
    }