package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.exceptionHandler.MyException;
import com.app.requestPayload.CalculatonRequest;
import com.app.service.CalculationService;

@RestController
public class TestController {

	@Autowired
	CalculationService calService;
	@GetMapping(value = "/test")
	public String  doTest() throws Exception {
      throw new Exception("Invalid Request");		
	}
	
	
	@GetMapping(value = "/simpleInterest")
	public String getInterest(@Valid @RequestBody CalculatonRequest request) {
		return calService.getSimpleInteres(request);
		
	}
}
