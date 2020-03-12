package com.java.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	//it is makes cache key
	@Cacheable(value="mycache")
	public String getUser(String admin) {
		// TODO Auto-generated method stub
		return "Brajesh Kumar";
	}

	//it is delete key from cache if found
	@CacheEvict(value="mycache",key = "admin")
	public String deleteUser(String admin) {
		// TODO Auto-generated method stub
		return "Brajesh Kumar";
	}
	//it is used for updated same key value
	@CachePut(value="mycache",key = "admin")
	public String updateUser(String admin) {
		// TODO Auto-generated method stub
		return "Brajesh Kumar";
	}
}
