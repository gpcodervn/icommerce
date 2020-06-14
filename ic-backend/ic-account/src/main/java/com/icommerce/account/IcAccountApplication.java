package com.icommerce.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.icommerce.account", "com.icommerce.core"})
public class IcAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcAccountApplication.class, args);
		log.info("Server [IcAccountApplication] started at " + LocalDateTime.now());
	}

}
