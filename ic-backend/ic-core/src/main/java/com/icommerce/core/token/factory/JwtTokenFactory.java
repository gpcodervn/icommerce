package com.icommerce.core.token.factory;

import com.icommerce.core.configuration.security.JwtSetting;
import com.icommerce.core.constant.Scope;
import com.icommerce.core.dto.AuthUserDetails;
import com.icommerce.core.dto.RoleDto;
import com.icommerce.core.dto.UserCredential;
import com.icommerce.core.token.AccessToken;
import com.icommerce.core.token.RefreshToken;
import com.icommerce.core.utils.JsonUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Factory class that should be always used to create {@link com.icommerce.core.token.JwtToken}.
 */
@Component
public class JwtTokenFactory {

    private static final String TOKEN_SCOPES_KEY = "scopes";

    private JwtSetting setting;

    @Autowired
    public JwtTokenFactory(JwtSetting setting) {
        this.setting = setting;
    }

    /**
     * Factory method for issuing new access JWT Tokens.
     */
    public AccessToken createAccessToken(AuthUserDetails userDetail) {
        if (userDetail == null) {
            throw new IllegalArgumentException("Cannot create JWT Token without auth user details");
        }

        UserCredential subject = UserCredential.builder()
                .id(userDetail.getId())
                .username(userDetail.getUsername())
                .roleCodes(extractRoleCodes(userDetail.getRoles())).build();

        List<String> scopes = userDetail.getAuthorities().stream().map(Object::toString).collect(Collectors.toList());

        Claims claims = Jwts.claims().setSubject(JsonUtils.toJson(subject));
        claims.put(TOKEN_SCOPES_KEY, scopes);

        String token = createToken(claims, setting.getAccessTokenExpirationInMs());
        return new AccessToken(token, subject);
    }

    private List<String> extractRoleCodes(Collection<RoleDto> roles) {
        return Optional.ofNullable(roles)
                .orElseGet(HashSet::new)
                .stream()
                .map(RoleDto::getCode)
                .collect(Collectors.toList());
    }

    /**
     * Factory method for issuing new refresh JWT Tokens.
     */
    public RefreshToken createRefreshToken(AuthUserDetails userDetail) {
        if (userDetail == null) {
            throw new IllegalArgumentException("Cannot create JWT Token without auth user details");
        }

        UserCredential subject = UserCredential.builder().id(userDetail.getId()).build();

        List<String> scopes = Collections.singletonList(Scope.REFRESH_TOKEN.authority());

        Claims claims = Jwts.claims().setSubject(JsonUtils.toJson(subject));
        claims.put(TOKEN_SCOPES_KEY, scopes);

        String token = createToken(claims, setting.getRefreshTokenExpirationInMs());
        return new RefreshToken(token);
    }

    private String createToken(Claims claims, int expirationInMs) {
        LocalDateTime currentTime = LocalDateTime.now();
        Date issueAt = Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
        Date expirationAt = Date.from(currentTime
                .plusMinutes(expirationInMs)
                .atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setClaims(claims)
                .setIssuer(setting.getTokenIssuer())
                .setIssuedAt(issueAt)
                .setExpiration(expirationAt)
                .signWith(SignatureAlgorithm.HS512, setting.getTokenSigningKey())
                .compact();
    }
}
