package com.mkomo.jorts.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

@Configuration
public class JortsMvcConfigurer implements WebMvcConfigurer {

	private static final long MAX_AGE_SECONDS = 3600;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//TODO lock this down
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
				.maxAge(getMaxAgeCors());

	}

	protected long getMaxAgeCors() {
		return MAX_AGE_SECONDS;
	}
}
