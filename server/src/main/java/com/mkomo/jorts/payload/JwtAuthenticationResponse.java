package com.mkomo.jorts.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mkomo.jorts.config.JortsFieldConfig;

import lombok.Data;

/**
 * Created by rajeevkumarsingh on 19/08/17.
 */
@Data
public class JwtAuthenticationResponse {

	@JsonProperty(JortsFieldConfig.ACCESS_TOKEN_KEY)
	private String accessToken;
	private String tokenType = "Bearer";

	public JwtAuthenticationResponse(String accessToken) {
		this.accessToken = accessToken;
	}
}
