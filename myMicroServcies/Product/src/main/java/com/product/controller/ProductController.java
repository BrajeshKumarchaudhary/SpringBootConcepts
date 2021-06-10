package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.ProductBean;
import com.product.service.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(path = "/product")
	@ApiOperation(value = "get product Listing API", tags = { "Product" })
	public ResponseEntity<List<ProductBean>> getProduct() {
		return new ResponseEntity<>(productService.getProductList(), HttpStatus.OK);
	}
}
