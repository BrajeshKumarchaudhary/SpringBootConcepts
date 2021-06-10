package com.app.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.data.Student;

@Controller
public class HomeController {

	@GetMapping(value = "/greeting")
	public String home(Model model) {
		model.addAttribute("name", "Brajesh");
		List<Student> all=new LinkedList<>();
		all.add(new Student("Brajesh", 888888));
		model.addAttribute("student", all);
		return "dashboard";
	}
}
