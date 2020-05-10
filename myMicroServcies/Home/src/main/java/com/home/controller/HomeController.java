package com.home.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
 public static  Logger log=LoggerFactory.getLogger(HomeController.class);
	@RequestMapping(value="/home")
	public Map index(HttpServletRequest request,@RequestParam Map<String,String> allRequestParams)
	{
	
		log.info("Request is comming"+allRequestParams.entrySet());
		return allRequestParams;
	}
	
	}
