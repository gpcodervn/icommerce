package com.icommerce.order.model.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class OrderResponse {

    private Long id;

    private String no;

    private Double price;

    private Long customerId;

    private LocalDateTime orderedAt;

    private Set<OrderDetailResponse> orderDetails;
}
