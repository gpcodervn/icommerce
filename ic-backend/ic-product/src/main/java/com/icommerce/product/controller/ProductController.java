package com.icommerce.product.controller;

import com.icommerce.core.annotations.CustomerAudit;
import com.icommerce.product.model.request.ProductFilterable;
import com.icommerce.product.model.request.ProductRequest;
import com.icommerce.product.model.response.ProductResponse;
import com.icommerce.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @CustomerAudit(feature = "product", action = "search")
    @GetMapping
    public Page<ProductResponse> findAll(@Valid ProductFilterable filterable) {
        log.info("findAll " + filterable);
        return productService.findAll(filterable);
    }

    @CustomerAudit(feature = "product", action = "view")
    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProductResponse create(@Valid @RequestBody ProductRequest request) {
        return productService.save(request);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
