package com.icommerce.core.configuration.security;

import com.icommerce.core.dto.UserCredential;
import com.icommerce.core.token.extractor.TokenExtractor;
import com.icommerce.core.token.parser.TokenParser;
import com.icommerce.core.token.verifier.TokenVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtSetting setting;

    @Autowired
    private TokenExtractor tokenExtractor;

    @Autowired
    private TokenVerifier tokenVerifier;

    @Autowired
    private TokenParser tokenParser;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = tokenExtractor.extract(request);
        log.debug("Auth filter with token " + jwt);

        if (StringUtils.hasText(jwt) && tokenVerifier.verify(jwt)) {
            UserCredential user = tokenParser.getUserCredential(jwt, setting.getTokenSigningKey());

            // UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
            // It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    user, null,
                    user.getRoleCodes().stream().map(roleCode -> new SimpleGrantedAuthority("ROLE_" + roleCode)).collect(Collectors.toList()));

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
