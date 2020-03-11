package com.java.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController implements ErrorController {

	
	private static final String path="/error";
	@GetMapping("/home")
	public String getHome()
	{
	   return "Welcome";
	}
	@GetMapping("/error")
	public String defaultError()
	{
	   return "Error";
	}
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return path;
	}
	
	
	
}
