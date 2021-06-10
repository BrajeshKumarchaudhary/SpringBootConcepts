package com.app.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping(value = "/test")
	public String test() {
		return " Hello World!";
	}

	@GetMapping(value = "set")
	public String setCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie("user", "bk");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(3);
		response.addCookie(cookie);
		return "Done";

	}

	@GetMapping(value = "read")
	public String readCookie(@CookieValue(name = "user") String user) {
		return "Hey I am " + user;
	}

}
