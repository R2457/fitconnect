package com.stackroute.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class ProfilePicConfig {

	
	@Bean
	public Cloudinary getCloudinary() {
		Map config= new HashMap<>();
		config.put("cloud_name", "dkrv2ka2k");
		config.put("api_key", "952961215675863");
		config.put("api_secret", "HkhlDhGtCXTrWjrZOP7V8WmyrkQ");
		config.put("secure", true);
		return new Cloudinary(config);
	}
}
