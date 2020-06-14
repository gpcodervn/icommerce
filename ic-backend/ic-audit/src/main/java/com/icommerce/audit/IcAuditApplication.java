package com.icommerce.audit;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@Log4j2
@SpringBootApplication(scanBasePackages = {"com.icommerce.audit", "com.icommerce.core"})
public class IcAuditApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcAuditApplication.class, args);
		log.info("Server [IcAuditApplication] started at " + LocalDateTime.now());
	}

}
