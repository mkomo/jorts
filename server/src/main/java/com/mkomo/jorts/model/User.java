package com.mkomo.jorts.model;

import java.util.HashSet;
import java.util.Set;

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

import com.mkomo.jorts.config.JortsFieldConfig;
import com.mkomo.jorts.model.audit.DateAudit;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by rajeevkumarsingh on 01/08/17.
 */

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = {
			"username"
		}),
		@UniqueConstraint(columnNames = {
			"email"
		})
})
public class User extends DateAudit {
	/**
	 *
	 */
	private static final long serialVersionUID = 2517455419566908795L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = JortsFieldConfig.USER_MAX_NAME_LENGTH, min = JortsFieldConfig.USER_MIN_NAME_LENGTH)
	private String name;

	@NotBlank
	@Size(max = JortsFieldConfig.USER_MAX_USERNAME_LENGTH, min = JortsFieldConfig.USER_MIN_USERNAME_LENGTH)
	private String username;

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

	public User() {

	}

	public User(String name, String username, String email, String password) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}
}
