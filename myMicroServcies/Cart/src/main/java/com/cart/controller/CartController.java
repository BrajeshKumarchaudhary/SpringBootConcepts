package com.cart.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan("com.cart")
public class CartController {

	@RequestMapping(value="/cart")
	public String cardDetail()
	{
		return "cardController";
	}
	
	
}
