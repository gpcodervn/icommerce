package com.icommerce.core.token;

import com.icommerce.core.dto.UserCredential;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Raw representation of JWT Token.
 */
@ToString
@AllArgsConstructor
public class AccessToken implements JwtToken {

    private final String token;

    @Getter
    private UserCredential subject;

    @Override
    public String getToken() {
        return this.token;
    }
}
