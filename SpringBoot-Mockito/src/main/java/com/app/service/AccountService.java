package com.app.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.model.Account;

@Service
public class AccountService {
	private static Map<Integer, Account> allAccountMap = new HashMap<>();
	static {
		allAccountMap.put(1, new Account(1, "33333333333", "Brajesh Kumar", "SBI Gurgoan", "SBS122201"));
		allAccountMap.put(2, new Account(2, "33333333333", "Ankit kumar", "SBI Gurgoan", "SBS122201"));
		allAccountMap.put(3, new Account(3, "33333333333", "Maneesh Kumar", "SBI Gurgoan", "SBS122201"));
		allAccountMap.put(4, new Account(4, "33333333333", "Govind", "SBI Gurgoan", "SBS122201"));
		allAccountMap.put(5, new Account(5, "33333333333", "Dheeraj Yadav", "SBI Gurgoan", "SBS122201"));
		allAccountMap.put(6, new Account(6, "33333333333", "NameLess", "SBI Gurgoan", "SBS122201"));
	}

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
