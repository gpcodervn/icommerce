package com.icommerce.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@Slf4j
@SpringBootApplication
public class IcCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcCoreApplication.class, args);
		log.info("Server [IcCoreApplication] started at " + LocalDateTime.now());
	}

}
