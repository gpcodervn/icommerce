### Guides

Add domains into the hosts file:

- Window : C:\Windows\System32\drivers\etc\hosts

- Mac OS : At Finder, press Command + Shift + G -> enter /etc or just open directly file "/private/etc/hosts"

- Add some domains:

> 127.0.0.1       icommerce.com
> 127.0.0.1       icommerce.vn
> 127.0.0.1       icommerce.au

Run the following command:

> mvn clean install

Start multiple applications by profiles:

> java -jar target/ic-discovery-server-0.0.1-SNAPSHOT.jar

If you would like to run multiple discovery server, let try with some other profiles:

> java -jar -Dspring.profiles.active=icommerce-au target/ic-discovery-server-0.0.1-SNAPSHOT.jar
> java -jar -Dspring.profiles.active=icommerce-vn target/ic-discovery-server-0.0.1-SNAPSHOT.jar

Don't like to start with built jar file, just use this command:

> mvn spring-boot:run -Dspring-boot.run.profiles=icommerce-au
> mvn spring-boot:run -Dspring-boot.run.profiles=icommerce-vn

Check server already run: 

> http://localhost:9000/
