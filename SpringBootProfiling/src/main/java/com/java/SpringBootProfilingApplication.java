package com.java;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
public class SpringBootProfilingApplication implements CommandLineRunner{
	@Autowired 
	DataSource dataSource;
	@Value(value="${welcome.message}")
	private String message;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootProfilingApplication.class, args);
	}
	
	@GetMapping(value="/profile")
	public String getString(HttpServletRequest request)
	{
		return this.message;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("dataSource"+dataSource);
		
	}

}
