package com.java.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.java.sevices.UserService;

@Component
public class JersyConfig extends ResourceConfig {

	public JersyConfig()
	{
		register(UserService.class);
	}
	
}
