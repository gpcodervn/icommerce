package com.icommerce.product.configuration.event;

import com.icommerce.core.constant.Auth;
import com.icommerce.core.dto.CustomerActivityDto;
import com.icommerce.core.dto.response.CustomerActivityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
@Slf4j
public class CustomerEventExecutor {

    private static final String CUSTOMER_AUDIT_URI = "http://localhost:8060/api/v1/customer-audits";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Run event on another Thread for a failure to store customer activity is completely transparent to customer
     * and should have no impact to the activity itself.
     */
    @Async
    public void executeInBackground(String jwt, CustomerActivityDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", Auth.BEARER_PREFIX + " " + jwt);

        HttpEntity<CustomerActivityDto> entity = new HttpEntity<>(dto, headers);

        ResponseEntity<CustomerActivityResponse> response = restTemplate.exchange(CUSTOMER_AUDIT_URI,
                HttpMethod.POST, entity, CustomerActivityResponse.class);
        log.debug("Already stored audit successfully with response " + response);
    }

}
