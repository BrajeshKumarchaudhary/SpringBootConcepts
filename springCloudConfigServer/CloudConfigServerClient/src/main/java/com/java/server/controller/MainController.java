package com.java.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@Autowired
	Environment env;
	@GetMapping(value="/getMainUrl")
	public String getMainUrl()
	{
		return env.getProperty("application.main.uri");
	}
	
}
