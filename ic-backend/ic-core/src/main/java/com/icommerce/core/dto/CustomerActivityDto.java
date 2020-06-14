package com.icommerce.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerActivityDto {

    @NotBlank
    private String action;

    @NotBlank
    private String feature;

    @NotBlank
    private String content;

    @NotNull
    private LocalDateTime triggeredAt;
}
