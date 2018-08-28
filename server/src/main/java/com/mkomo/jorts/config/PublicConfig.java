package com.mkomo.jorts.config;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

@Data
public class PublicConfig {

	@JsonValue
	private final Map<String, Object> staticFields;

	public PublicConfig() {
		Field[] declaredFields = this.getClass().getDeclaredFields();
		staticFields = new LinkedHashMap<>();
		for (Field field : declaredFields) {
			if (Modifier.isStatic(field.getModifiers())) {
				try {
					staticFields.put(field.getName(), field.get(this));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					staticFields.put(field.getName(), null);
				}
			}
		}
	}
}
