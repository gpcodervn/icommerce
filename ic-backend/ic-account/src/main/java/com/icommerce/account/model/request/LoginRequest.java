package com.icommerce.account.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {

    @NotBlank
    @Email
    @Size(min = 4, max = 256)
    private String userName;

    @NotBlank
    @Size(min = 4, max = 128)
    private String password;

}