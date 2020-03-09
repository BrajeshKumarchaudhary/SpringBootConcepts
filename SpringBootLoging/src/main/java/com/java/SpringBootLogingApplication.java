package com.java;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
public class SpringBootLogingApplication {
     org.slf4j.Logger log=LoggerFactory.getLogger(SpringBootLogingApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringBootLogingApplication.class, args);
	}
	
	@GetMapping(value="/api")
	public String getString(HttpServletRequest request)
	{
		log.info(request.getRequestURI());
		return "Hello";
	}

}
