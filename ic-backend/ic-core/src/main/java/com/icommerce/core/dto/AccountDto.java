package com.icommerce.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

	private Long id;

	private String username;

	private String password;

	private Boolean active;

	private List<RoleDto> roles;

}
