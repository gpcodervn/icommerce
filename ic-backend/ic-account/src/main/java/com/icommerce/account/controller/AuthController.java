package com.icommerce.account.controller;

import com.icommerce.account.model.request.LoginRequest;
import com.icommerce.account.model.response.JwtAuthenticationToken;
import com.icommerce.account.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    @ResponseStatus(code = HttpStatus.OK)
    public JwtAuthenticationToken login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
