package com.icommerce.core.token.factory;

import com.icommerce.core.configuration.security.JwtSetting;
import com.icommerce.core.constant.AuthRole;
import com.icommerce.core.dto.AccountDto;
import com.icommerce.core.dto.AuthUserDetails;
import com.icommerce.core.dto.RoleDto;
import com.icommerce.core.token.AccessToken;
import com.icommerce.core.token.RefreshToken;
import com.icommerce.core.token.parser.TokenParser;
import com.icommerce.core.token.parser.TokenParserImpl;
import com.icommerce.core.utils.EncrytedPasswordUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class JwtTokenFactoryTest {

    private JwtSetting setting;

    private JwtTokenFactory jwtTokenFactory;

    private TokenParser tokenParser;

    @BeforeEach
    public void prepareForTest() {
        setting = mockJwtSetting();
        jwtTokenFactory = new JwtTokenFactory(setting);
        tokenParser = new TokenParserImpl();
    }

    private JwtSetting mockJwtSetting() {
        JwtSetting setting = new JwtSetting();
        setting.setTokenSigningKey("icommerce-test");
        setting.setAccessTokenExpirationInMs(1 * 60 * 1000);
        setting.setRefreshTokenExpirationInMs(5 * 60 * 1000);
        setting.setTokenIssuer("https://icommerce.com/");
        return setting;
    }

    @Test
    public void givenAuthUser_WhenCreateAccessToken_ShouldReturnAccessToken() {
        AuthUserDetails userDetail = new AuthUserDetails(createAccountDto());
        AccessToken token = jwtTokenFactory.createAccessToken(userDetail);
        Assertions.assertNotNull(token.getToken());
        Assertions.assertEquals(1L, token.getSubject().getId().longValue());
        Assertions.assertEquals("ptgiang56it@gmail.com", token.getSubject().getUsername());
        Assertions.assertEquals(AuthRole.ANONYMOUS.name(), token.getSubject().getRoleCodes().get(0));
        Assertions.assertEquals(token.getSubject(), tokenParser.getUserCredential(token.getToken(), setting.getTokenSigningKey()));
    }

    @Test
    public void givenNullAuthUser_WhenCreateAccessToken_ThrowExeption() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            jwtTokenFactory.createAccessToken(null);
        });
    }

    @Test
    public void givenAuthUser_WhenCreateRefreshToken_ShouldReturnAccessToken() {
        AuthUserDetails userDetail = new AuthUserDetails(createAccountDto());
        RefreshToken token = jwtTokenFactory.createRefreshToken(userDetail);
        Assertions.assertNotNull(token.getToken());
        Assertions.assertEquals(1L, tokenParser.getUserIdFromJWT(token.getToken(), setting.getTokenSigningKey()).longValue());
    }

    @Test
    public void givenNullAuthUser_WhenRefreshToken_ThrowExeption() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            jwtTokenFactory.createRefreshToken(null);
        });
    }

    private AccountDto createAccountDto() {
        return AccountDto.builder()
                .id(1L)
                .active(true)
                .username("ptgiang56it@gmail.com")
                .password(EncrytedPasswordUtils.encrytePassword("12345678"))
                .roles(createRoles())
                .build();
    }

    private List<RoleDto> createRoles() {
        RoleDto role = RoleDto.builder().code(AuthRole.ANONYMOUS.name()).id(1L).name("Guest").build();
        return Collections.singletonList(role);
    }
}
