package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.aspect.ApplicationLog;
import com.app.aspect.ExceptionThrow;
import com.app.aspect.LogExecutionTime;
import com.app.model.User;
import com.app.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	@GetMapping(value = "getUser")
	@ApplicationLog
	@LogExecutionTime
	public List<User> getUser(@RequestParam("userId") int userId){
		return userService.getAllUser(userId);
	}
	
	@GetMapping(value = "get")
//	@ApplicationLog
//	@LogExecutionTime
	@ExceptionThrow
	public List<User> get(@RequestParam("userId") int userId) throws Exception{
		throw new Exception("No User Found");
	}
	
	
	@GetMapping(value = "fault")
	@HystrixCommand(fallbackMethod = "defaultRes")
	public List<User> checkFault(@RequestParam("userId") int userId) throws Exception{
		throw new Exception("No User Found");
	}
	
	
	public List<User> defaultRes(@RequestParam("userId") int userId) throws Exception{
		List<User> user=new ArrayList<User>();
		user.add(new User());
	    return user;
	}
}
