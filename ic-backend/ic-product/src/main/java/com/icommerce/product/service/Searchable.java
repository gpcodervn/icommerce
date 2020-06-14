package com.icommerce.product.service;

import org.springframework.data.jpa.domain.Specification;

@FunctionalInterface
public interface Searchable<T, P> {

    Specification<T> search(P param);
}
