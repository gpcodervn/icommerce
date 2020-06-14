package com.icommerce.order.model.response;

import lombok.Data;

@Data
public class OrderDetailResponse {

    private Long id;

    private Long productId;

    private String name;

    private Double price;

    private Integer amount;

    private String branchName;

    private String color;
}
