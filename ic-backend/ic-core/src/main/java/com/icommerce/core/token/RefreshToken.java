package com.icommerce.core.token;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Raw representation of JWT Token.
 */
@ToString
@AllArgsConstructor
public class RefreshToken implements JwtToken {

    private final String token;

    @Override
    public String getToken() {
        return this.token;
    }
}