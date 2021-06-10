package com.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping(value = "/getHomeData")
	public ResponseEntity<String> getData() {
		return new ResponseEntity<>("Welcome", HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getFriendsData")
	public ResponseEntity<Map> getFriendData() {
		Map<String,String> contactList=new HashMap<>();
		contactList.put("Brajesh Kumar", "99562303000");
		return new ResponseEntity<>(contactList, HttpStatus.OK);
	}

}
