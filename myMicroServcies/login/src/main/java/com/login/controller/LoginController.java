package com.login.oauth;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/google")
@CrossOrigin(value = "http://localhost:6060")
public class LoginController {

	@RequestMapping(value = "/login")
	public String login(Principal principal,Model model) {
		String url="http://localhost:6060/dashboard";
		return "redirect:" + url;
	}
}
