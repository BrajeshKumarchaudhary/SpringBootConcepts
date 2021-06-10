package com.home.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.dto.ProductBean;
import com.home.service.ProductService;

@RestController
public class HomeRestController {
	public static Logger log = LoggerFactory.getLogger(HomeRestController.class);
	@Autowired
	private ProductService productService;

	@GetMapping(value = "/getProductList")
	public ResponseEntity<List<ProductBean>> getProductList() {
		log.info("Home Controller is calling----product Service");
		return new ResponseEntity<>(productService.getProductData(), HttpStatus.OK);
	}

}
