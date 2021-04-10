package com.app.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Account;

import lombok.Data;

@Service
@Data
public class AccountService {
	@Autowired
	AccountCalculationService accountCalService;
	
	private static Map<Integer, Account> allAccountMap = new HashMap<>();
	

	public void AccountService() {

	}

	public Account getAccountInfo(Integer accountId) throws Exception {
		if (allAccountMap.containsKey(accountId)) {
			return allAccountMap.get(accountId);
		}
		throw new Exception("Invalid Account Number.");
	}
	
	public boolean  getBoolean(Integer accountId) throws Exception {
     return false;
	}

}
