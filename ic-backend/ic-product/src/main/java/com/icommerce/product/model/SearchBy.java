package com.icommerce.product.model;

import lombok.Getter;

public enum SearchBy {

    NAME("name"), BRANCH_NAME("branchName"), COLOR("color");

    @Getter
    private String fieldName;

    SearchBy(String fieldName) {
        this.fieldName = fieldName;
    }
}
