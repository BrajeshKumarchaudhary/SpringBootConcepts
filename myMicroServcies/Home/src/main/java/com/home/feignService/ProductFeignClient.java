package com.home.feignService;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.home.config.ProductFeignClientConfig;
import com.home.dto.ProductBean;

@FeignClient(name = "productservice", configuration = ProductFeignClientConfig.class)
public interface ProductFeignClient {

	@GetMapping(value = "/product")
	public List<ProductBean> getProduct();
}
