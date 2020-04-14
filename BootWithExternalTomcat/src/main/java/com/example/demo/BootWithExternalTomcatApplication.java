package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BootWithExternalTomcatApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootWithExternalTomcatApplication.class, args);
	}
	
	@GetMapping(value = "/tests")
	public String test()
	{
		return "deployed and done ";
	}

}
