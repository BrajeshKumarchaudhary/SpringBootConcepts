package com.product.service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.product.dto.ProductBean;

@Service
public class ProductService {

	public List<ProductBean> getProductList() {
		List<ProductBean> productList = new LinkedList<>();
		Calendar calendar = Calendar.getInstance();
		ProductBean product = new ProductBean();
		product.setProductId(101);
		product.setProductName("Oil Body");
		product.setCreatedBy("Brajesh Kumar");
		product.setCreatedAt(calendar);
		product.setUpdtedAt(calendar);
		productList.add(product);
		return productList;
	}

}
