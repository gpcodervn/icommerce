package com.icommerce.product.configuration.event;

import com.icommerce.core.constant.Auth;
import com.icommerce.core.dto.CustomerActivityDto;
import com.icommerce.core.dto.response.CustomerActivityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class CustomerEventExecutor {

    private static final String AUDIT_SERVICE_ID = "ic-audit";
    private static final String CUSTOMER_AUDIT_URI = "/api/v1/customer-audits";

    private final RestTemplate restTemplate;

    private final LoadBalancerClient loadBalancerClient;

    @Autowired
    public CustomerEventExecutor(RestTemplate restTemplate, LoadBalancerClient loadBalancerClient) {
        this.restTemplate = restTemplate;
        this.loadBalancerClient = loadBalancerClient;
    }

    /**
     * Run event on another Thread for a failure to store customer activity is completely transparent to customer
     * and should have no impact to the activity of customer.
     */
    @Async
    public void executeInBackground(String jwt, CustomerActivityDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", Auth.BEARER_PREFIX + " " + jwt);

        HttpEntity<CustomerActivityDto> entity = new HttpEntity<>(dto, headers);

        getAuditUrl().ifPresentOrElse(url -> {
            String postUrl = url + CUSTOMER_AUDIT_URI;
            log.debug("Audit server URL: " + postUrl);
            ResponseEntity<CustomerActivityResponse> response = restTemplate.exchange(postUrl,
                    HttpMethod.POST, entity, CustomerActivityResponse.class);
            log.debug("Already stored audit successfully with response " + response);
        }, () -> log.error("Can not find ic-audit server"));
    }

    private Optional<String> getAuditUrl() {
        ServiceInstance serviceInstance = loadBalancerClient.choose(AUDIT_SERVICE_ID);
        return Optional.ofNullable(serviceInstance.getUri()).map(Objects::toString);
    }

}
