package com.mkomo.jorts.model.audit;

import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by rajeevkumarsingh on 19/08/17.
 */

@MappedSuperclass
@JsonIgnoreProperties(
		value = {"createdBy", "updatedBy"},
		allowGetters = true
)
@Data
@EqualsAndHashCode(callSuper=true)
public abstract class UserDateAudit extends DateAudit {

	/**
	 *
	 */
	private static final long serialVersionUID = 2265296250807560553L;

	@CreatedBy
	private Long createdBy;

	@LastModifiedBy
	private Long updatedBy;
}
