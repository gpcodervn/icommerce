### Guides

Please make sure, you already started ic-discovery server.

Run the following command:

> mvn clean install

> java -jar -Dspring.profiles.active=dev /target/ic-product-0.0.1-SNAPSHOT.jar

Start multiple applications by profiles:

> java -jar -Dspring.profiles.active=dev,ic-product-replica01 /target/ic-product-0.0.1-SNAPSHOT.jar

> java -jar -Dspring.profiles.active=dev,ic-product-replica02 /target/ic-product-0.0.1-SNAPSHOT.jar

## Don't like to start with built jar file, just use this command:

> mvn spring-boot:run -Dspring-boot.run.profiles=dev

> mvn spring-boot:run -Dspring-boot.run.profiles=dev,ic-product-replica01

> mvn spring-boot:run -Dspring-boot.run.profiles=dev,ic-product-replica02

### Check API POST

> POST http://localhost:8090/api/v1/products

with JSON body:

    {
    	"name": "iphone 8 Plus",
    	"description": "The best produt in 2020",
    	"price": 1499,
    	"branchName": "Apple",
    	"color": "pink"
    }

Authorization:

    Bearer token: please use API /v1/auth/sign-in
    
You will receive a response:

    {
        "id": 5,
        "name": "iphone 8 Plus",
        "description": "The best produt in 2020",
        "price": 1499.0,
        "branchName": "Apple",
        "color": "pink"
    }
    
### Check API GET list

> GET http://localhost:8090/api/v1/products?keyword=apple&limit=5&page=0&sort=-name&sort=-price&minPrice=1000&maxPrice=2000

Authorization:

    Bearer token: please use API /v1/auth/sign-in
    
You will receive a response:

    {
        "content": [
            {
                "id": 6,
                "name": "iphone X",
                "description": "The best produt in 2020",
                "price": 1999.0,
                "branchName": "Apple",
                "color": "pink"
            },
            {
                "id": 5,
                "name": "iphone 8 Plus",
                "description": "The best produt in 2020",
                "price": 1499.0,
                "branchName": "Apple",
                "color": "pink"
            }
        ],
        "pageable": {
            "sort": {
                "unsorted": false,
                "sorted": true,
                "empty": false
            },
            "offset": 0,
            "pageNumber": 0,
            "pageSize": 5,
            "paged": true,
            "unpaged": false
        },
        "last": true,
        "totalPages": 1,
        "totalElements": 2,
        "size": 5,
        "number": 0,
        "sort": {
            "unsorted": false,
            "sorted": true,
            "empty": false
        },
        "numberOfElements": 2,
        "first": true,
        "empty": false
    }

### Other API

GET by Id

> GET http://localhost:8090/api/v1/products/5

DELETE by Id

> DELETE http://localhost:8090/api/v1/products/5

### Audit the activities of customers

For audit support, all customers' activities such as searching, filtering and viewing product's details will be stored in the database.
The failure to store customer activity is completely transparent to customer and have no impact to the activity itself.

You can check this point in the table `customer_activity`, the data will be automatically saved every time you call to api GET products or GET products/{id}

If there is no data saved, please check whether the ic-audit server is running or not.
