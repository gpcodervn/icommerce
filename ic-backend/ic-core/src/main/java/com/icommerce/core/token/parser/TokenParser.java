package com.icommerce.core.token.parser;

import com.icommerce.core.dto.UserCredential;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface TokenParser {

    /**
     * Parses and validates JWT Token signature.
     *
     * @return Jws<Claims> The Claims of token
     */
    Jws<Claims> parseClaims(String token, String signingKey);

    /**
     * Get the user credential
     */
    UserCredential getUserCredential(String token, String signingKey);

    /**
     * Get the user credential
     */
    UserCredential getUserCredential(Jws<Claims> claimsJws);

    /**
     * Get user id from jwt token
     */
    Long getUserIdFromJWT(String token, String signingKey);
}
