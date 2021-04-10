package com.app.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

//@SpringBootTest
//@RunWith(SpringRunner.class)
public class AccountTxnTestController {

	@Autowired
	MockMvc mockMvc;

//    @Autowired
//    protected WebApplicationContext wac;

    @Autowired
    AccountController accountController;
	
	@Before
	void setUp() {
	}
	
	
	@Test
	@DisplayName("Testing name is equal or not")
	public void test1() {
		assertEquals("failure - strings are not equal", "text", "text1");
	}

	@Test
	@DisplayName("Check Value is false or not")
	public void checkFalse() {
		assertFalse("failure - should be false", false);
	}
	
	@Test
	@DisplayName("Testing Account Controller Amount.")
	public void testControllerMethod() throws Exception {
		mockMvc.perform(get("/getAccountAmount").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}
}
