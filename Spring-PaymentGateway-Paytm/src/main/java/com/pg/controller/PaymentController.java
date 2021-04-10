package com.pg.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pg.response.TransactionResponse;
import com.pg.service.TransactionService;

@RestController
@RequestMapping(value = "/txn/")
public class PaymentController {

   @Autowired
   private TransactionService transactionService;

	@PostMapping(value = "/doTxn")
	public ModelAndView getRedirect(@RequestParam(name = "CUST_ID") String customerId,
			@RequestParam(name = "TXN_AMOUNT") String transactionAmount,
			@RequestParam(name = "ORDER_ID") String orderId) throws Exception {
		return transactionService.doTxn(customerId,transactionAmount,orderId);
	}

	@PostMapping(value = "/pgresponse")
	public TransactionResponse getResponseRedirect(HttpServletRequest request, Model model) {
		return transactionService.transactionResponse(request, model);
	}

}
