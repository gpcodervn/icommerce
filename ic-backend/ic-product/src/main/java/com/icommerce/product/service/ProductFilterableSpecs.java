package com.icommerce.product.service;

import com.icommerce.product.entity.Product;
import com.icommerce.product.model.SearchBy;
import com.icommerce.product.model.SortBy;
import com.icommerce.product.model.request.ProductFilterable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ProductFilterableSpecs implements Searchable<Product, ProductFilterable>,
        Sortable<ProductFilterable>, Pageable<ProductFilterable> {

    private static final String SORT_ASC = "+";
    private static final String SORT_DESC = "-";
    private static final String PRICE_COLUMN = "price";

    @Override
    public Specification<Product> search(ProductFilterable filterable) {
        return buildSpecification(filterable);
    }

    @Override
    public Sort sort(ProductFilterable filterable) {
        return buildMultipleSorts(filterable.getSort());
    }

    @Override
    public org.springframework.data.domain.Pageable page(ProductFilterable filterable) {
        return PageRequest.of(filterable.getPage(), filterable.getLimit(), sort(filterable));
    }

    private Sort buildMultipleSorts(String[] params) {
        if (ArrayUtils.isEmpty(params)){
            return Sort.unsorted();
        }

        List<Sort> sorts = Arrays.stream(params).map(this::buildSorts)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (sorts.isEmpty()) {
            return Sort.unsorted();
        }
        Sort sort = sorts.get(0);
        for (int i = 1; i < sorts.size(); i++) {
            sort.and(sorts.get(i));
        }
        return sort;
    }

    @Nullable
    private Sort buildSorts(String param) {
        if(StringUtils.isBlank(param)) {
            return null;
        }
        param = param.trim();
        if (param.startsWith(SORT_DESC)) {
            String columnName = param.substring(1);
            if (SortBy.isAllowedSortByField(columnName)) {
                return Sort.by(columnName).descending();
            }
        } else {
            String columnName = param;
            if (param.startsWith(SORT_ASC)) {
                columnName = param.substring(1);
            }
            if (SortBy.isAllowedSortByField(columnName)) {
                return Sort.by(columnName).ascending();
            }
        }
        return null;
    }

    private Specification<Product> buildSpecification(ProductFilterable filterable) {
        return (root, query, cb) -> {
            QueryBuilder queryBuilder = new QueryBuilder<>(cb, root);
            if (StringUtils.isNotBlank(filterable.getKeyword())) {
                for(SearchBy searchBy : SearchBy.values()) {
                    queryBuilder = queryBuilder.likeIgnoreCase(searchBy.getFieldName(), filterable.getKeyword());
                }
            }
            Predicate[] orPredicates = queryBuilder.build();

            Predicate[] andPredicates = new QueryBuilder<>(cb, root)
                    .greaterThanOrEqualTo(PRICE_COLUMN, filterable.getMinPrice())
                    .lessThanOrEqualTo(PRICE_COLUMN, filterable.getMaxPrice())
                    .build();

            if (orPredicates.length > 0 && andPredicates.length > 0) {
                return cb.and(cb.or(orPredicates), cb.and(andPredicates));
            }
            if (orPredicates.length > 0) {
                return cb.or(orPredicates);
            }
            if (andPredicates.length > 0) {
                return cb.and(andPredicates);
            }
            return null;
        };
    }
}
