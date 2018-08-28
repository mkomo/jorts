package com.mkomo.jorts.payload;

import lombok.Data;

/**
 * Created by rajeevkumarsingh on 19/08/17.
 */
@Data
public class ApiResponse {
	private Boolean success;
	private String message;

	public ApiResponse(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}
}
