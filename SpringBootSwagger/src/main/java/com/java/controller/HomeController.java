package com.java.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "home")
public class HomeController {

	
	@GetMapping(value="/index/{id}")
	public String getHomeData(@PathVariable("id") int id)
	{
		return "home"+id;
	}
}
