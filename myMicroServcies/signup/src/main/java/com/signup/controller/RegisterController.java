package com.signup.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.signup.model.SignUp;
import com.signup.service.SignUpService;

@RestController
public class RegisterController {
	@Autowired
	SignUpService signup;

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String register() {
		String user_name = "Brajeskumar";
		String user_passwd = "Kumar";
		String user_email = "bk@gmail.com";
		Date dob = new Date();
		int user_role = 1;
		int is_active = 1;
		try {
		Map<String,Integer>user =signup.userSave(user_name, user_passwd, user_email, dob, user_role, is_active);
		  System.out.print(user.values());  
		
		
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.print(ex.getStackTrace());
		}
		return "Something went wrong";
	}

	@GetMapping("/list")
	public List check() {
		return signup.getUser();
	}

	@GetMapping("/id")
	public List getUserId() {
		int id=15;
		return signup.getUser(id);
	}
	
	
}
