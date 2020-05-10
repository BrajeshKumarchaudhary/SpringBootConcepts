package com.signup.repo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.signup.model.SignUp;
@org.springframework.stereotype.Repository
@Component
public interface SignupRepository  extends CrudRepository<SignUp, Long>{
 
	@Procedure(name = "registerUser")
	Map<String,Integer> userSave(@Param("user_name") String user_name,@Param("user_passwd") String user_passwd,
			@Param("user_email") String user_email,@Param("dob") Date dob,
			@Param("user_role") int user_role,@Param("is_active") int is_active);
	
	List findAll();
	List findById(int id);
	
	}
