package com.icommerce.core.configuration.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.jwt")
@Data
public class JwtSetting {

    /**
     * Key is used to sign JwtToken
     */
    private String tokenSigningKey;

    /**
     * JwtToken will expire after this time.
     */
    private int accessTokenExpirationInMs;

    /**
     * Use this token to get a new JwtToken when access token expired
     */
    private int refreshTokenExpirationInMs;

    /**
     * Token issuer
     */
    private String tokenIssuer;
}
