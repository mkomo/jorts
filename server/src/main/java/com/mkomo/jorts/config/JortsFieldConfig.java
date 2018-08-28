package com.mkomo.jorts.config;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class JortsFieldConfig extends PublicConfig {

	public static final int ROLE_NAME_LENGTH = 40;

	public static final int USER_MAX_NAME_LENGTH = 250;
	public static final int USER_MIN_NAME_LENGTH = 1;

	public static final int USER_MAX_USERNAME_LENGTH = 60;
	public static final int USER_MIN_USERNAME_LENGTH = 1;

	public static final int USER_MAX_EMAIL_LENGTH = 254;
	public static final int USER_MIN_EMAIL_LENGTH = 5;

	public static final int USER_MAX_PASSWORD_LENGTH = 250;
	public static final int USER_MIN_PASSWORD_LENGTH = 6;

	public static final String ACCESS_TOKEN_KEY = "accessToken";

}
