package com.app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.model.Account;
import com.app.service.AccountCalculationService;
import com.app.service.AccountService;
import com.app.service.LoggerService;
import com.app.service.UserService;

/*
 * by Annoating this Spring create One context for Whole Classes.and create bean of whole class.
 */
@SpringBootTest
/*
 * //By this Spring context register only specified class. create only these beans.
 */
//@ContextConfiguration(classes = {AccountService.class,AccountCalculationService.class,UserService.class}) 
@RunWith(SpringRunner.class)

public class AccountTestController {

	@Autowired
	AccountService accountService;
	
	/*
	 * When Any Service is calling Any other Service one Way to injects by @Before and init function.like this.
	 */
//	@Before
//	public void init() {
//		AccountCalculationService service1=new AccountCalculationService();
//		 accountService.setAccountCalService(service1);
//	}
	
	
	/*
	 * When any service interacting any IO operation like DataBase.Reading Value from dataBase then use @MockBean and initalized the service.
	 * By Using this it is return dummy response  from each function and avoid to calling dataBase.
	 */
	@MockBean
	UserService userService;
	
	
	/*
	 * When Any service need to avoid calling any function then used @SpyBean
	 */
	
	@SpyBean
	LoggerService loggerService;
	
	
	
	@Test
	public void integrationTesting() {
		LinkedList<Integer> l=new LinkedList<>();
		l.add(200);
		Mockito.when(userService.getUserId()).thenReturn(l);
		LinkedList<Integer> l1=userService.getUserId();
//		assertThat(l1).isEmpty();
		
	}

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
