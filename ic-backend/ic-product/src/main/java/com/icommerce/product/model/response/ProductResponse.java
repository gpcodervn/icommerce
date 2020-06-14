package com.icommerce.product.model.response;

import lombok.Data;

@Data
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private String branchName;

    private String color;
}
