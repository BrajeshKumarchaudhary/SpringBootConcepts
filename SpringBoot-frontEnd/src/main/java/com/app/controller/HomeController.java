package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	
	/*
	 * For Passing data to View Used Model and set Attribute
	 */
	@GetMapping(value = "/")
	public String home() {
		return "home";
	}
}
