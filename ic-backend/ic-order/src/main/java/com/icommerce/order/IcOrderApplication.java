package com.icommerce.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.time.LocalDateTime;

@Slf4j
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.icommerce.order", "com.icommerce.core"})
public class IcOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcOrderApplication.class, args);
		log.info("Server [IcOrderApplication] started at " + LocalDateTime.now());
	}

}
