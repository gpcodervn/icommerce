package com.icommerce.order.service;

import com.icommerce.order.model.request.OrderRequest;
import com.icommerce.order.model.response.OrderResponse;

/**
 * The service for handling the business logic of order service
 */
public interface OrderService {

    /**
     * Create an order.
     *
     * @param request The order data will be stored
     * @return The saved order
     */
    OrderResponse save(OrderRequest request);
}
