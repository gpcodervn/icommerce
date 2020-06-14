package com.icommerce.order.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderDetailRequest {

    @NotNull
    private Long productId;

    @NotNull
    @Length( max = 128)
    private String name;

    @NotNull
    private Double price;

    @NotNull
    @Length(max = 64)
    private String branchName;

    private String color;

    @NotNull
    @Min(value = 1)
    private Integer amount;
}
