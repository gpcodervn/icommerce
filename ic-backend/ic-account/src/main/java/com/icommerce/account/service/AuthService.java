package com.icommerce.account.service;

import com.icommerce.account.model.request.LoginRequest;
import com.icommerce.account.model.response.JwtAuthenticationToken;

public interface AuthService {

    JwtAuthenticationToken login(LoginRequest request);
}
