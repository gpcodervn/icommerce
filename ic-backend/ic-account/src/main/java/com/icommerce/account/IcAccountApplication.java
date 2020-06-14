package com.icommerce.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.time.LocalDateTime;

@Slf4j
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.icommerce.account", "com.icommerce.core"})
public class IcAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcAccountApplication.class, args);
		log.info("Server [IcAccountApplication] started at " + LocalDateTime.now());
	}

}
