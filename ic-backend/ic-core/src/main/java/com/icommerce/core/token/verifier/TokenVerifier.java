package com.icommerce.core.token.verifier;

/**
 * Check whether the token is valid
 */
public interface TokenVerifier {

    boolean verify(String token);
}
