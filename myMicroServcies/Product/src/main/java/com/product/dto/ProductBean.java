package com.product.dto;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductBean {

	private int productId;
	private String productName;
	private String productCategory;
	private String createdBy;
	private Calendar createdAt;
	private Calendar updtedAt;
	
}


