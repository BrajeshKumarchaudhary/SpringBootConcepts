package com.app.contoller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//If u need cross Origin in whole Controller the go class Level Cross Origin
//@CrossOrigin(origins= "http://localhost:8882")
public class UserTestController {
	
	/*
	 * For Enabling Cross Origin If U have only one API method .then go for method Level Cross origin
	 */
	@GetMapping(value = "access")
//	@CrossOrigin(origins= "http://localhost:8882")
	public String getUserName() {
		return "Brajesh Kumar";
	}
	
}
