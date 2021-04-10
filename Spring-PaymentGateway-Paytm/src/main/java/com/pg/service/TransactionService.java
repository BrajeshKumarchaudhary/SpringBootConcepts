package com.pg.service;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.pg.config.PaytmDetails;
import com.pg.response.TransactionResponse;
import com.pg.util.PaymentMessage;
import com.pg.util.TransactionServiceHelper;

@Service
public class TransactionService {

	@Autowired
	private TransactionServiceHelper serviceHelper;

	@Autowired
	private PaytmDetails paytmDetails;
	@Autowired
	private Environment env;

	public ModelAndView doTxn(String customerId, String transactionAmount, String orderId) throws Exception {
		ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetails.getPaytmUrl());
		TreeMap<String, String> parameters = new TreeMap<>();
		paytmDetails.getDetails().forEach((k, v) -> parameters.put(k, v));
		parameters.put("MOBILE_NO", env.getProperty("paytm.mobile"));
		parameters.put("EMAIL", env.getProperty("paytm.email"));
		parameters.put("ORDER_ID", orderId);
		parameters.put("TXN_AMOUNT", transactionAmount);
		parameters.put("CUST_ID", customerId);
		String checkSum = TransactionServiceHelper.getCheckSum(parameters, paytmDetails);
		parameters.put("CHECKSUMHASH", checkSum);
		modelAndView.addAllObjects(parameters);
		return modelAndView;
	}

	public TransactionResponse transactionResponse(HttpServletRequest request, Model model) {
		TransactionResponse response = new TransactionResponse();
		Map<String, String[]> mapData = request.getParameterMap();
		TreeMap<String, String> parameters = new TreeMap<String, String>();
		mapData.forEach((key, val) -> parameters.put(key, val[0]));
		String paytmChecksum = "";
		if (mapData.containsKey("CHECKSUMHASH")) {
			paytmChecksum = mapData.get("CHECKSUMHASH")[0];
		}
		boolean isValideChecksum = false;
		try {
			isValideChecksum = TransactionServiceHelper.validateCheckSum(parameters, paytmChecksum, paytmDetails);
			if (isValideChecksum && parameters.containsKey("RESPCODE")) {
				if (parameters.get("RESPCODE").equals("01")) {
					response.setMessage(PaymentMessage.SUCCESS_TXN_MESSAGE);
					response.setResponseCode(PaymentMessage.SUCCESS_TXN_CODE);
				} else {
					response.setMessage(PaymentMessage.FAILED_TXN_MESSAGE);
					response.setResponseCode(PaymentMessage.FAILED_TXN_CODE);
				}
			} else {
				response.setMessage(PaymentMessage.CHECKSUM_TXN_MESSAGE);
				response.setResponseCode(PaymentMessage.CHECKSUM_TXN_CODE);
			}
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setResponseCode(PaymentMessage.TXN_EXCEPTION_CODE);
		}
		parameters.remove("CHECKSUMHASH");
		response.setParameters(parameters);
		return response;
	}
}
