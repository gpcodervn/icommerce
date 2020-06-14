package com.icommerce.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:jwt.properties"})
public class ResourceConfiguration {
}
