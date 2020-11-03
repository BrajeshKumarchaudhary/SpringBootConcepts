package com.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.db1.entity.LeadUsers;
import com.app.db1.repo.LeadUserRepo;

@RestController
public class TestController {

	@Autowired
	LeadUserRepo repo;
	@GetMapping(value = "/test")
	public List<LeadUsers> getUserList(){
		return repo.findAll();
	}
	
}
