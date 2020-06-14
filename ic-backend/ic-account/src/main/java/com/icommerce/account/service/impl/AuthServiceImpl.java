package com.icommerce.account.service.impl;

import com.icommerce.account.model.request.LoginRequest;
import com.icommerce.account.model.response.JwtAuthenticationToken;
import com.icommerce.account.service.AuthService;
import com.icommerce.core.constant.Auth;
import com.icommerce.core.dto.AuthUserDetails;
import com.icommerce.core.token.AccessToken;
import com.icommerce.core.token.factory.JwtTokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;

    private JwtTokenFactory tokenFactory;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenFactory tokenFactory) {
        this.authenticationManager = authenticationManager;
        this.tokenFactory = tokenFactory;
    }

    @Override
    public JwtAuthenticationToken login(LoginRequest loginRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        return this.createResponse((AuthUserDetails) auth.getPrincipal());
    }

    private JwtAuthenticationToken createResponse(AuthUserDetails userDetail) {
        AccessToken accessToken = tokenFactory.createAccessToken(userDetail);
        return JwtAuthenticationToken.builder()
                .accessToken(accessToken.getToken())
                .refreshToken(tokenFactory.createRefreshToken(userDetail).getToken())
                .tokenType(Auth.BEARER_PREFIX)
                .build();
    }
}
