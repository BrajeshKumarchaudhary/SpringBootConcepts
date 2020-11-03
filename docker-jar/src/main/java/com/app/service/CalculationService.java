package com.app.service;

import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

import com.app.requestPayload.CalculatonRequest;

@Service
public class CalculationService {

	public String getSimpleInteres(CalculatonRequest request)
	{
		double interest=(request.getPrice()*request.getRate()*request.getYear())/100;
		DecimalFormat df = new DecimalFormat("####0.00");
		return df.format(interest);
	}
}
