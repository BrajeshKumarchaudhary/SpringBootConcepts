package com.auth.app.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.auth.app.model.InputData;

@Repository
public interface InputDataRepo extends JpaRepository<InputData, Long> {
	@Modifying
	@Transactional
    @Query("UPDATE InputData input SET input.dataValue = :value WHERE input.dataKey = :tokenExpires")
    int updateTokenExpire(@Param("value") String value, @Param("tokenExpires") String tokenExpires);
	
	@Modifying
	@Transactional
    @Query("UPDATE InputData input SET input.dataValue = :value WHERE input.dataKey = :token_key")
	int updateAccessToken(@Param("value") String Accesstoken, @Param("token_key") String key);
}