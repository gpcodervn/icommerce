package com.icommerce.product.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.util.Date;

@Data
@NoArgsConstructor
public class ProductFilterable {

    private String keyword;

    private Double minPrice;

    private Double maxPrice;

    /**
     * Multiple sort request parameters.
     * For example: sort=+firstName&sort=-lastName
     */
    private String[] sort;

    @Min(value = 0)
    private int page = 0;

    @Min(value = 1)
    private int limit = 10;
}
