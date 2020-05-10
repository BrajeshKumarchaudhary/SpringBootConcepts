package com.signup.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.signup.model.SignUp;
import com.signup.repo.SignupRepository;
import com.signup.repo.SignuprepoPagination;

@Service
public class SignUpService {
	@Autowired
	SignupRepository repo;

	@Autowired
	SignuprepoPagination repopage;
	
	public Map userSave(String user_name, String user_passwd, String user_email, Date dob, int user_role,
			int is_active) {
		Map<String, Integer> result = null;
		try {
			result = repo.userSave(user_name, user_passwd, user_email, dob, user_role, is_active);

		} catch (Exception ex) {
			System.out.print(ex.getMessage());
		}
		return result;
	}

	public List getUser() {
		List list = null;
		try {
			return repo.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public List getUser(int id) {
		List list = null;
		try {
			return repo.findById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

}
