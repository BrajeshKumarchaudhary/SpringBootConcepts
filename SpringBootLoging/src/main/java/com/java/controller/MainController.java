package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.bean.Person;

@RestController
public class MainController {

//	@Autowired
//	private Person person;
//
//	@Autowired
//	private Person person1;

	@GetMapping(value = "/test")
	public String test() {
		return "Welcome buddy.";
	}

	@GetMapping(value = "/test2")
	public ResponseEntity<String> test1() {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<String>("ok", headers, HttpStatus.OK);
	}
}
