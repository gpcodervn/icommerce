package com.icommerce.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Slf4j
public class AuthUserDetails implements UserDetails {

    private static final long serialVersionUID = -883489449957337175L;

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private List<RoleDto> roles;

    private List<GrantedAuthority> authorities;

    public AuthUserDetails(AccountDto account) {
        log.info("account " + account);
        this.id = account.getId();
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.roles = account.getRoles();
        this.authorities = createAuthorities(roles);
    }

    public static List<GrantedAuthority> createAuthorities(List<RoleDto> roles) {
        return Optional.ofNullable(roles)
                .orElseGet(ArrayList::new)
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getCode()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
