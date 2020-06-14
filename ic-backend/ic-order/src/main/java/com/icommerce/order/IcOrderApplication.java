package com.icommerce.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.icommerce.order", "com.icommerce.core"})
public class IcOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcOrderApplication.class, args);
	}

}
