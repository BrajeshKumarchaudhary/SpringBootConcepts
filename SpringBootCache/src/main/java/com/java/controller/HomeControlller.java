package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.service.UserService;

@RestController
public class HomeControlller {
      @Autowired
      UserService user;
	

      
   @GetMapping(value = "/index")
	public String getHome()
	{
		return user.getUser("brajesh");
	}
	
}
