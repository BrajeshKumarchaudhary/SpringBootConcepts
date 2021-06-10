package com.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.dto.ProductBean;
import com.home.feignService.ProductFeignClient;

@Service
public class ProductService {

	@Autowired
	private ProductFeignClient productFeignCleint;
	
	public List<ProductBean> getProductData(){
		return productFeignCleint.getProduct();
	}
}
