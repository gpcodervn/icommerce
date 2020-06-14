package com.icommerce.product.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {

    @NotBlank
    @Length( max = 128)
    private String name;

    @Length( max = 512)
    private String description;

    @NotNull
    private Double price;

    @NotBlank
    @Length(max = 64)
    private String branchName;

    private String color;
}
