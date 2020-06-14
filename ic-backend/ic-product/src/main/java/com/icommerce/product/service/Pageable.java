package com.icommerce.product.service;

@FunctionalInterface
public interface Pageable<P> {

    org.springframework.data.domain.Pageable page(P param);
}
