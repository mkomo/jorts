package com.mkomo.jorts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mkomo.jorts.config.JortsFieldConfig;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

	@GetMapping("/fields")
	public JortsFieldConfig getFieldConfig() {
		return new JortsFieldConfig();
	}

}
