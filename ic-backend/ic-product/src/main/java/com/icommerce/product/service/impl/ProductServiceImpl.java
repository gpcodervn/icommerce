package com.icommerce.product.service.impl;

import com.icommerce.core.exception.ResourceNotFoundException;
import com.icommerce.product.entity.Product;
import com.icommerce.product.entity.ProductHistory;
import com.icommerce.product.model.request.ProductFilterable;
import com.icommerce.product.model.request.ProductRequest;
import com.icommerce.product.model.response.ProductResponse;
import com.icommerce.product.repository.ProductHistoryRepository;
import com.icommerce.product.repository.ProductRepository;
import com.icommerce.product.service.ProductFilterableSpecs;
import com.icommerce.product.service.ProductService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductHistoryRepository productHistoryRepository;

    private final ProductFilterableSpecs productFilterableSpecs;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductHistoryRepository productHistoryRepository, ProductFilterableSpecs productFilterableSpecs) {
        this.productRepository = productRepository;
        this.productHistoryRepository = productHistoryRepository;
        this.productFilterableSpecs = productFilterableSpecs;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ProductResponse> findAll(ProductFilterable filterable) {
        ModelMapper modelMapper = new ModelMapper();
        Specification<Product> searchable = productFilterableSpecs.search(filterable);
        Pageable pageable = productFilterableSpecs.page(filterable);
        return productRepository.findAll(searchable, pageable)
                .map(entity -> modelMapper.map(entity, ProductResponse.class));
    }

    @Transactional(readOnly = true)
    @Override
    public ProductResponse findById(Long id) {
        Product product = getProduct(id);
        return buildResponse(product);
    }

    @Transactional
    @Override
    public ProductResponse save(ProductRequest request) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(request, Product.class);
        Product savedPatient = productRepository.save(product);
        return buildResponse(savedPatient);
    }

    @Transactional
    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = getProduct(id);
        saveHistory(product);
        return update(product, request);
    }

    private void saveHistory(Product product) {
        ModelMapper modelMapper = new ModelMapper();
        ProductHistory productHistory = modelMapper.map(product, ProductHistory.class);
        productHistory.setId(null);
        productHistory.setProductId(product.getId());
        productHistoryRepository.save(productHistory);
    }

    private ProductResponse update(Product product, ProductRequest request) {
        mergeFields(request, product);
        Product savedPatient = productRepository.save(product);
        return buildResponse(savedPatient);
    }

    private void mergeFields(Object source, Object destination) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(source, destination);
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch(EmptyResultDataAccessException e) {
            return false;
        }
    }

    private Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("The product could not be found with the given id [" + id + "]"));
    }

    private ProductResponse buildResponse(Product product) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(product, ProductResponse.class);
    }
}
