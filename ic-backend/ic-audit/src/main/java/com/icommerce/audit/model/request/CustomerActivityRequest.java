package com.icommerce.audit.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CustomerActivityRequest {

    @NotBlank
    private String action;

    @NotBlank
    private String feature;

    @NotBlank
    private String content;

    @NotNull
    private LocalDateTime triggeredAt;
}
