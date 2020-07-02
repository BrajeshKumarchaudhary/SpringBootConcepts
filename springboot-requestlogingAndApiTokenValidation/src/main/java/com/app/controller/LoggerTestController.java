package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggerTestController {

	@GetMapping(value = "/test")
	public String test()
	{
		return "Okay";
	}
	
}
