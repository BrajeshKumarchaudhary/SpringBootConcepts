package com.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.model.Account;
import com.app.service.AccountService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountTestController {

	@Autowired
	AccountService accountService;

	@Test
	@DisplayName("This Is Used for Testing of Account getting Exacly or not")
	public void testAccountById() throws Exception {
		Account acc = accountService.getAccountInfo(10);
		assertEquals(acc.getAccountNumber(), "33333333333");
		Assertions.assertFalse(accountService.getBoolean(2));
	}

	@Test
	@DisplayName("Testing With Assertions")
	public void getBoolean() throws Exception {
		Assertions.assertFalse(accountService.getBoolean(2));
	}

	@Test
	@DisplayName("Testing With Assert")
	public void testWithBooleanAssert() throws Exception {
		boolean flag = accountService.getBoolean(1);
		assertEquals(flag, false);
	}

	@Test
	@DisplayName("Testing With Mockito")
	public void testWithBooleanMockito() throws Exception {
		Mockito.when(accountService.getBoolean(1)).thenReturn(false);
	}
}
