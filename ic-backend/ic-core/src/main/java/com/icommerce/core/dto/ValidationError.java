package com.icommerce.core.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class ValidationError {

    private String code;

    @Nullable
    private String message;
}
