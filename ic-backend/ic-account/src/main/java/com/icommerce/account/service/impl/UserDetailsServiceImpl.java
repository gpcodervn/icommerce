package com.icommerce.account.service.impl;

import com.icommerce.account.entity.Account;
import com.icommerce.account.repository.AccountRepository;
import com.icommerce.core.dto.AccountDto;
import com.icommerce.core.dto.AuthUserDetails;
import com.icommerce.core.dto.RoleDto;
import com.icommerce.core.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private AccountRepository accountRepository;

	@Autowired
	public UserDetailsServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		Account account = accountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
		return createUserDetails(account);
	}

	public UserDetails loadUserById(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id [" + id + "] is not found"));
		return createUserDetails(account);
	}

	private UserDetails createUserDetails(Account account) {
		List<RoleDto> roles = account.getRoles()
				.stream()
				.map(role -> RoleDto.builder().id(role.getId()).code(role.getCode()).name(role.getName()).build())
				.collect(Collectors.toList());
		AccountDto dto = AccountDto.builder().id(account.getId())
				.username(account.getUsername())
				.password(account.getPassword())
				.active(account.getActive())
				.roles(roles)
				.build();
		return new AuthUserDetails(dto);
	}
}
