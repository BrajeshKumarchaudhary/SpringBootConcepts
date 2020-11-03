package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.interceptor.ApiKeyVerify;

@RestController
public class LoggerTestController {

	@GetMapping(value = "/test")
	@ApiKeyVerify
	public String test()
	{
		return "Okay";
	}
	
	
	@GetMapping(value = "/test1")
	public String test1()
	{
		return "Okay";
	}
	
}
