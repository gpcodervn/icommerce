package com.icommerce.product.service;


import org.springframework.data.domain.Sort;

@FunctionalInterface
public interface Sortable<P> {

    Sort sort(P param);
}
