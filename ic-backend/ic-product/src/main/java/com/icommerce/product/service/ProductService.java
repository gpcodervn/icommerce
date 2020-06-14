package com.icommerce.product.service;

import com.icommerce.product.model.request.ProductFilterable;
import com.icommerce.product.model.request.ProductRequest;
import com.icommerce.product.model.response.ProductResponse;
import org.springframework.data.domain.Page;

/**
 * The service for handling the business logic of product service
 */
public interface ProductService {

    /**
     * Get list products based on the criteria
     *
     * @param filterable The criteria for getting products
     * @return The list products
     */
    Page<ProductResponse> findAll(ProductFilterable filterable);

    /**
     * Get a product.
     *
     * @param id The id attribute of the product entity
     * @return The product
     */
    ProductResponse findById(Long id);

    /**
     * Save a product.
     *
     * @param request The product data will be stored
     * @return The saved product
     */
    ProductResponse save(ProductRequest request);

    /**
     * Update a product.
     *
     * @param id The id attribute of the product entity
     * @param request The product data will be stored
     * @return The updated product
     */
    ProductResponse update(Long id, ProductRequest request);

    /**
     * Delete a product.
     *
     * @param id The id attribute of the product entity
     * @return true if deleted successfully, otherwise return false
     */
    boolean deleteById(Long id);
}