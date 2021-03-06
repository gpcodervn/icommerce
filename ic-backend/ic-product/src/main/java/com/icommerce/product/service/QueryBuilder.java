package com.icommerce.product.service;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class QueryBuilder<T> {

    private CriteriaBuilder cb;

    private Root<T> root;

    private List<Predicate> predicates = new ArrayList<>();

    QueryBuilder(CriteriaBuilder cb, Root<T> root) {
        this.cb = cb;
        this.root = root;
    }

    QueryBuilder likeIgnoreCase(String propertyPath, String value) {
        if (StringUtils.isNotBlank(value)) {
            predicates.add(cb.like(cb.upper(root.get(propertyPath)), "%" + StringUtils.upperCase(value) + "%"));
        }
        return this;
    }

    QueryBuilder equalsIgnoreCase(String propertyPath, String value) {
        if (StringUtils.isNotBlank(value)) {
            predicates.add(cb.equal(cb.upper(root.get(propertyPath)), StringUtils.upperCase(value)));
        }
        return this;
    }

    QueryBuilder equals(String propertyPath, Object value) {
        if (value != null && StringUtils.isNotBlank(value.toString())) {
            predicates.add(cb.equal(root.get(propertyPath), value));
        }
        return this;
    }

    QueryBuilder greaterThanOrEqualTo(String propertyPath, Double value) {
        if (value != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(propertyPath), value));
        }
        return this;
    }

    QueryBuilder lessThanOrEqualTo(String propertyPath, Double value) {
        if (value != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get(propertyPath), value));
        }
        return this;
    }

    Predicate[] build() {
        return predicates.toArray(new Predicate[0]);
    }
}