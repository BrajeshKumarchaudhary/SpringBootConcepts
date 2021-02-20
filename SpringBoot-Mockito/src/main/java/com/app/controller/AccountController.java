package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Account;
import com.app.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@GetMapping(value = "/getAccountDetail")
	public Account getAccountDetail(@RequestParam(value = "accountId") Integer accountId) throws Exception {
		return accountService.getAccountInfo(accountId);
	}
}
