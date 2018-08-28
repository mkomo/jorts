package com.mkomo.jorts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.mkomo.jorts.config.JortsFieldConfig;

import lombok.Data;

/**
 * Created by rajeevkumarsingh on 01/08/17.
 */
@Entity
@Table(name = "roles")
@Data
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(length = JortsFieldConfig.ROLE_NAME_LENGTH)
	private RoleName name;

	public Role() {

	}

	public Role(RoleName name) {
		this.name = name;
	}

}
