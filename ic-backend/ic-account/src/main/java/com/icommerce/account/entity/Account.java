package com.icommerce.account.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "account", catalog = "icommerce", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Account extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "username", unique = true, nullable = false, length = 128)
	private String username;

	@Column(name = "password", nullable = false, length = 128)
	private String password;

	@Column(name = "active")
	private Boolean active;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "account_role", catalog = "icommerce", joinColumns = {
			@JoinColumn(name = "account_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", nullable = false, updatable = false) })
	private Set<Role> roles;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	private Set<LinkedSocialAccount> linkedSocialAccounts;

}
