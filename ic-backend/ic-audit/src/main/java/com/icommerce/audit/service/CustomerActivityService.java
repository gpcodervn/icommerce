package com.icommerce.audit.service;

import com.icommerce.audit.model.request.CustomerActivityRequest;
import com.icommerce.audit.model.response.CustomerActivityResponse;

/**
 * The service for handling the business logic of customer audit service
 */
public interface CustomerActivityService {

    /**
     * Create an order.
     *
     * @param request The customer activity will be stored
     * @return The saved customer activity
     */
    CustomerActivityResponse save(CustomerActivityRequest request);
}
