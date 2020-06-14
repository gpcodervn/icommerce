package com.icommerce.audit.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerActivityResponse {
    private Long id;

    private String action;

    private String feature;

    private String content;

    private Long createdBy;

    private LocalDateTime createdAt;
}
