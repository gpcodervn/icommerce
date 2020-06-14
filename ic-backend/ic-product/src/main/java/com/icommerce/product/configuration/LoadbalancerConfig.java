package com.icommerce.product.configuration;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClient(name = "server", configuration = RibbonConfiguration.class)
public class LoadbalancerConfig {

}
