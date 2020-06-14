package com.icommerce.core.token.verifier;

import com.icommerce.core.configuration.security.JwtSetting;
import com.icommerce.core.token.parser.TokenParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExpirationTokenVerifier implements TokenVerifier {

    private final JwtSetting setting;

    private final TokenParser tokenParser;

    @Autowired
    public ExpirationTokenVerifier(JwtSetting setting, TokenParser tokenParser) {
        this.setting = setting;
        this.tokenParser = tokenParser;
    }

    @Override
    public boolean verify(String token) {
        return tokenParser.parseClaims(token, setting.getTokenSigningKey()) != null;
    }
}
