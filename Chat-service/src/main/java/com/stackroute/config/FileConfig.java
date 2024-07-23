package com.stackroute.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class FileConfig {

	
	@Bean
	public Cloudinary getCloudinary() {
		Map config= new HashMap<>();
		config.put("cloud_name", "dazzlahbf");
		config.put("api_key", "967885868234864");
		config.put("api_secret", "5dXAinw7ywQX9xK-nPiCWtXMb_Y");
		config.put("secure", true);
		return new Cloudinary(config);
	}
}

