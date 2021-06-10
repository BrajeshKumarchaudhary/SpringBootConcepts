package com.app;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SignInGoogleOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SignInGoogleOauthApplication.class, args);
	}

	@GetMapping(value = "/welcome")
	public String greeting() {
		return "Welcome to my portal";
	}
	@GetMapping(value = "/user")
	public Principal user(Principal principal) {
		System.out.println(principal.getName());
		return principal;
	}

}
