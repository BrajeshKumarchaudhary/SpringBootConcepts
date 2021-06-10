package com.app.repo;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.app.model.ProductInfo;

@EnableScan
public interface ProductRep extends CrudRepository<ProductInfo, String> {
	Optional<ProductInfo> findById(String id);
}
