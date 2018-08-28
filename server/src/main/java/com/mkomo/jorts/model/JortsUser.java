package com.mkomo.jorts.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.mkomo.jorts.config.JortsFieldConfig;
import com.mkomo.jorts.model.audit.DateAudit;
import com.mkomo.jorts.security.UserPrincipal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {
			"username"
		}),
		@UniqueConstraint(columnNames = {
			"email"
		})
})
public class JortsUser extends DateAudit{

	/**
	 *
	 */
	private static final long serialVersionUID = -6088080624044945571L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//TODO move this to non-jorts profile
	@NotBlank
	@Size(max = JortsFieldConfig.USER_MAX_NAME_LENGTH, min = JortsFieldConfig.USER_MIN_NAME_LENGTH)
	private String name;

	@NotBlank
	@Size(max = JortsFieldConfig.USER_MAX_USERNAME_LENGTH, min = JortsFieldConfig.USER_MIN_USERNAME_LENGTH)
	private String username;

	//TODO remove this in favor of much more general identity table.
	@NaturalId
	@NotBlank
	@Size(max = JortsFieldConfig.USER_MAX_EMAIL_LENGTH, min = JortsFieldConfig.USER_MIN_EMAIL_LENGTH)
	@Email
	private String email;

	@NotBlank
	@Size(max = JortsFieldConfig.USER_MAX_PASSWORD_LENGTH, min = JortsFieldConfig.USER_MIN_PASSWORD_LENGTH)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public UserPrincipal asUserPrincipal() {
		JortsUser user = this;
		List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
				new SimpleGrantedAuthority(role.getName().name())
		).collect(Collectors.toList());

		return new UserPrincipal(
				user.getId(),
				user.getName(),
				user.getUsername(),
				user.getEmail(),
				user.getPassword(),
				user.getCreatedAt(),
				authorities
		);
	}

}
