package com.icommerce.product.model;

import lombok.Getter;

import java.util.stream.Stream;

public enum SortBy {
    NAME("name"), BRANCH_NAME("branchName"), PRICE("price");

    @Getter
    private String fieldName;

    SortBy(String fieldName) {
        this.fieldName = fieldName;
    }

    public static boolean isAllowedSortByField(String fieldName) {
        return Stream.of(SortBy.values()).anyMatch(sortBy -> sortBy.fieldName.equals(fieldName));
    }
}
