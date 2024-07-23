package com.stackroute.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTenplateConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
