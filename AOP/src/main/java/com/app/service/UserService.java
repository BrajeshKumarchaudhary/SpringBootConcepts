package com.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.aspect.LogExecutionTime;
import com.app.model.User;


@Service
public class UserService {
	@LogExecutionTime
	public List<User> getAllUser(int userId) {
		return Arrays.asList(new User(1,"Brajesh", "Google"));
	}
}
