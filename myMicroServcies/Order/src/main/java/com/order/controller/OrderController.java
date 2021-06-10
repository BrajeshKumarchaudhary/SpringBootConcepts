package com.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	@RequestMapping(value="/order")
	public String orderDeatil()
	{
		return "OrderController";
	}
	
	
}
