package com.icommerce.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.time.LocalDateTime;

@Slf4j
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.icommerce.product", "com.icommerce.core"})
public class IcProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcProductApplication.class, args);
		log.info("Server [IcProductApplication] started at " + LocalDateTime.now());
	}

}
